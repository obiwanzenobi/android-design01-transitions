package pl.lightmobile.design01transitions

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.transition.Fade
import androidx.transition.Slide
import androidx.transition.TransitionSet

class TransitionSetFragment : Fragment() {

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        val transitionSet = TransitionSet()
        val interpolator = AccelerateDecelerateInterpolator()

        val firstBarTransition = Slide(Gravity.TOP).addTarget(R.id.topBar).setInterpolator(interpolator)
        val leftBarTransition = Slide(Gravity.START).addTarget(R.id.leftBar).setInterpolator(interpolator)
        val bottomBarTransition = Slide(Gravity.BOTTOM).addTarget(R.id.bottomBar).setInterpolator(interpolator)
        val rightBarTransition = Slide(Gravity.END).addTarget(R.id.rightBar).setInterpolator(interpolator)
        val contentTransition = Fade().addTarget(R.id.content)

        transitionSet
            .addTransition(firstBarTransition)
            .addTransition(leftBarTransition)
            .addTransition(bottomBarTransition)
            .addTransition(rightBarTransition)
            .addTransition(contentTransition)
            .setDuration(1000)
            .setOrdering(TransitionSet.ORDERING_SEQUENTIAL)

        enterTransition = transitionSet
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_transition_set, container, false)
    }
}
