package pl.lightmobile.design01transitions

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.Fade
import androidx.transition.SidePropagation

class TransitionPropagationFragment : Fragment() {

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        val fade = Fade()

        val propagation = SidePropagation()
        fade.propagation = propagation

        enterTransition = fade
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_propagation, container, false)
    }
}
