package at.allaboutapps.app.features.main

import android.os.Bundle
import android.view.View
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import at.allaboutapps.app.R
import at.allaboutapps.app.base.BaseFragment
import at.allaboutapps.app.databinding.FragmentClubDetailBinding
import at.allaboutapps.app.features.main.Constants.KEY_CLUB
import at.allaboutapps.app.networking.model.Club
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding

class ClubDetailFragment : BaseFragment(R.layout.fragment_club_detail) {
    private val binding by viewBinding(FragmentClubDetailBinding::bind)
    private var club: Club? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            club = it.getSerializable(KEY_CLUB) as Club?
        }
        binding.tvName.text = club!!.name
        binding.tvDescription.text = HtmlCompat.fromHtml(
            context?.resources!!.getString(
                R.string.clubs_description,
                club!!.name,
                club!!.european_titles
            ), HtmlCompat.FROM_HTML_MODE_LEGACY
        )

        Glide.with(requireContext()).load(club!!.image).into(binding.ivClub)
    }
}
