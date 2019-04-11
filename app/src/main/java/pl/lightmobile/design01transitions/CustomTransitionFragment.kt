package pl.lightmobile.design01transitions

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater

class CustomTransitionFragment : Fragment() {

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        enterTransition = TransitionInflater.from(context).inflateTransition(R.transition.custom_transition)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_custom_transition, container, false)
    }
}
