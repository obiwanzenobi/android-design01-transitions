package pl.lightmobile.design01transitions

import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.cardview.widget.CardView
import androidx.transition.Transition
import androidx.transition.TransitionValues

private const val EXTRA_BACKGROUND = "ChangeColorTransition.background"

class ChangeColorTransition : Transition() {

    override fun captureStartValues(transitionValues: TransitionValues) {
        captureValues(transitionValues)
    }

    override fun captureEndValues(transitionValues: TransitionValues) {
        captureValues(transitionValues)
    }

    override fun createAnimator(
        sceneRoot: ViewGroup,
        startValues: TransitionValues?,
        endValues: TransitionValues?
    ): Animator? {
        if (null == startValues || null == endValues) {
            return null
        }

        val view = endValues.view
        val startBackground = startValues.values[EXTRA_BACKGROUND]
        val endBackground = endValues.values[EXTRA_BACKGROUND]
        return when {
            startBackground is ColorDrawable
                && endBackground is ColorDrawable
                && startBackground.color != endBackground.color -> colorAnimator(
                startBackground.color,
                endBackground.color,
                view
            )
            startBackground is Int && endBackground is Int && startBackground != endBackground -> colorAnimator(
                startBackground,
                endBackground,
                view
            )
            else -> null
        }
    }

    private fun colorAnimator(@ColorInt startColor: Int, @ColorInt endColor: Int, view: View): ValueAnimator {
        val animator = ValueAnimator.ofObject(ArgbEvaluator(), startColor, endColor)
        animator.addUpdateListener { animation ->
            val value = animation.animatedValue
            if (value == null) {
                return@addUpdateListener
            }
            if (view is CardView) {
                view.setCardBackgroundColor(value as Int)
            } else {
                view.setBackgroundColor(value as Int)
            }

        }
        return animator
    }

    private fun captureValues(values: TransitionValues) {
        val view = values.view
        if (view is CardView) {
            values.values[EXTRA_BACKGROUND] = view.cardBackgroundColor.defaultColor
        } else {
            values.values[EXTRA_BACKGROUND] = view.background
        }
    }
}
