package pl.lightmobile.design01transitions

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.transition.TransitionValues
import androidx.transition.Visibility

private const val DEFAULT_BEFORE = 0.7F
private const val DEFAULT_AFTER = 1.0F

class ScaleVisibilityTransition : Visibility {

    private var before: Float = DEFAULT_BEFORE
    private var after: Float = DEFAULT_AFTER

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val attrArray = context.obtainStyledAttributes(attrs, R.styleable.ScaleVisibilityTransition)

        before = attrArray.getFloat(R.styleable.ScaleVisibilityTransition_before, DEFAULT_BEFORE)
        after = attrArray.getFloat(R.styleable.ScaleVisibilityTransition_after, DEFAULT_AFTER)

        attrArray.recycle()
    }

    constructor(before: Float = 0.7f, after: Float = 1.0f) : super() {
        this.before = before
        this.after = after
    }

    override fun onAppear(
        sceneRoot: ViewGroup?,
        view: View,
        startValues: TransitionValues?,
        endValues: TransitionValues?
    ): Animator {
        view.scaleX = before
        view.scaleY = before

        return ValueAnimator.ofFloat(before, after).apply {

            addUpdateListener { animation ->
                val value = animation.animatedValue
                if (value == null) {
                    return@addUpdateListener
                }

                view.scaleX = value as Float
                view.scaleY = value
            }
            interpolator = FastOutSlowInInterpolator()
        }
    }

    override fun onDisappear(
        sceneRoot: ViewGroup?,
        view: View,
        startValues: TransitionValues?,
        endValues: TransitionValues?
    ): Animator {
        view.scaleX = after
        view.scaleY = after

        return ValueAnimator.ofFloat(after, before).apply {

            addUpdateListener { animation ->
                val value = animation.animatedValue
                if (value == null) {
                    return@addUpdateListener
                }

                view.scaleX = value as Float
                view.scaleY = value
            }
            interpolator = FastOutSlowInInterpolator()
        }
    }
}
