package pl.lightmobile.design01transitions

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.transition.Slide
import androidx.transition.TransitionManager
import kotlinx.android.synthetic.main.fragment_simple_transitions.button
import kotlinx.android.synthetic.main.fragment_simple_transitions.root
import kotlinx.android.synthetic.main.fragment_simple_transitions.secondButton

class SimpleTransitionsFragment : Fragment() {

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        enterTransition = Slide(Gravity.TOP)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_simple_transitions, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        button.setOnClickListener {
            TransitionManager.beginDelayedTransition(root)
            secondButton.isVisible = true
        }
        secondButton.setOnClickListener {
            TransitionManager.beginDelayedTransition(root)
            secondButton.isVisible = false
        }
    }
}
