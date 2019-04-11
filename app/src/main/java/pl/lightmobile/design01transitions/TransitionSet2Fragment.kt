package pl.lightmobile.design01transitions

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater

class TransitionSet2Fragment : Fragment() {

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        enterTransition = TransitionInflater.from(context).inflateTransition(R.transition.second_set)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_transition_set_second, container, false)
    }
}
