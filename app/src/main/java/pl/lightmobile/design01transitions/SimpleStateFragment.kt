package pl.lightmobile.design01transitions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.transition.TransitionManager
import kotlinx.android.synthetic.main.fragment_simple_state_transitions.errorMessage
import kotlinx.android.synthetic.main.fragment_simple_state_transitions.loader
import kotlinx.android.synthetic.main.fragment_simple_state_transitions.root
import pl.lightmobile.design01transitions.SimpleStateViewModel.SimpleState.Error
import pl.lightmobile.design01transitions.SimpleStateViewModel.SimpleState.Loading

class SimpleStateFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_simple_state_transitions, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val vm = ViewModelProviders.of(this)[SimpleStateViewModel::class.java]

        vm.viewState.observeNotNull(this) { state ->
            when(state) {
                Loading -> showLoadingState()
                Error -> showErrorState()
            }
        }
        vm.loadData()
        errorMessage.setOnClickListener { vm.loadData() }
    }

    private fun showErrorState() {
        TransitionManager.beginDelayedTransition(root)
        loader.visibility = View.GONE
        errorMessage.visibility = View.VISIBLE
    }

    private fun showLoadingState() {
        TransitionManager.beginDelayedTransition(root)
        loader.visibility = View.VISIBLE
        errorMessage.visibility = View.GONE
    }
}
