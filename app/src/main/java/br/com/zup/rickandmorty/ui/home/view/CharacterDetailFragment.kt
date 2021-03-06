package br.com.zup.rickandmorty.ui.home.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.zup.rickandmorty.CHARACTER_KEY
import br.com.zup.rickandmorty.data.model.CharacterResult
import br.com.zup.rickandmorty.databinding.FragmentCharacterDetailBinding
import com.squareup.picasso.Picasso

class CharacterDetailFragment : Fragment() {

    private lateinit var binding : FragmentCharacterDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as HomeActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getCharacterDetail()
    }

    private fun getCharacterDetail(){
        val character = arguments?.getParcelable<CharacterResult>(CHARACTER_KEY)

        character?.let {
            Picasso.get().load(it.image).into(binding.ivCharacterPhoto)
            binding.tvNome.text = it.name
            binding.tvEspecie.text = it.species
            binding.tvGenero.text = it.gender
            binding.tvStatus.text = it.status
            (activity as HomeActivity).supportActionBar?.title = it.name
        }
    }


}