package br.com.zup.rickandmorty.ui.home.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import br.com.zup.rickandmorty.ui.viewstate.ViewState
import br.com.zup.rickandmorty.CHARACTER_KEY
import br.com.zup.rickandmorty.R
import br.com.zup.rickandmorty.data.model.CharacterResult
import br.com.zup.rickandmorty.databinding.FragmentListBinding
import br.com.zup.rickandmorty.ui.home.adapter.CharacterAdapter
import br.com.zup.rickandmorty.ui.home.viewmodel.CharacterListViewModel

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding

    private val viewModel: CharacterListViewModel by lazy {
        ViewModelProvider(this)[CharacterListViewModel::class.java]
    }

    private val adapter: CharacterAdapter by lazy {
        CharacterAdapter(arrayListOf(), this::goToCharacterDetail)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as HomeActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as HomeActivity).supportActionBar?.setTitle(R.string.app_name)

            showRecyclerView()
            viewModel.getAllCharacters()
            initObserver()
    }


    private fun initObserver() {
        viewModel.characterList.observe(this.viewLifecycleOwner) {

            when (it) {
                is ViewState.Success -> {
                    adapter.updateCharacterList(it.data.toMutableList())
                }
                is ViewState.Error -> {
                    Toast.makeText(
                        context,
                        "${it.throwable.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {}
            }
        }
    }


    private fun showRecyclerView() {

        binding.rvCharacter.adapter = adapter
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvCharacter.layoutManager = layoutManager
    }

    private fun goToCharacterDetail(character: CharacterResult) {
        val bundle = bundleOf(CHARACTER_KEY to character)
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_listFragment_to_characterDetailFragment, bundle)
    }
}