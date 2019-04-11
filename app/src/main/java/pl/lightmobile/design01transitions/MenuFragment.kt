package pl.lightmobile.design01transitions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_menu.customTransition
import kotlinx.android.synthetic.main.fragment_menu.propagation
import kotlinx.android.synthetic.main.fragment_menu.setTransitions
import kotlinx.android.synthetic.main.fragment_menu.setTransitions2
import kotlinx.android.synthetic.main.fragment_menu.simpleTransitions
import kotlinx.android.synthetic.main.fragment_menu.stateTransitions

class MenuFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_menu, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        simpleTransitions.setOnClickListener {
            findNavController().navigate(R.id.toSimpleTransitions)
        }
        stateTransitions.setOnClickListener {
            findNavController().navigate(R.id.toStateTransitions)
        }
        setTransitions.setOnClickListener {
            findNavController().navigate(R.id.toTransitionSet)
        }
        setTransitions2.setOnClickListener {
            findNavController().navigate(R.id.toSecondTransitionSet)
        }
        propagation.setOnClickListener {
            findNavController().navigate(R.id.toPropagation)
        }
        customTransition.setOnClickListener {
            findNavController().navigate(R.id.toCustom)
        }
    }
}
