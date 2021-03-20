package com.atef.clubhouse.data.remote.feature.home.mapper

import com.atef.clubhouse.data.base.mapper.ListRemoteModelMapper
import com.atef.clubhouse.data.base.mapper.RemoteModelMapper
import com.atef.clubhouse.data.remote.feature.auth.model.ProfileResponse
import com.atef.clubhouse.data.remote.feature.home.model.ChannelResponse
import com.atef.clubhouse.domain.entity.auth.User
import com.atef.clubhouse.domain.entity.home.Channel
import javax.inject.Inject

class ChannelMapper @Inject constructor(private val listMapper: ListRemoteModelMapper<ProfileResponse?, User>) : RemoteModelMapper<ChannelResponse, Channel> {
    override fun mapFromModel(model: ChannelResponse): Channel {
        return with(model) {
            Channel(channelId = channelId!!,
                    channel = channel,
                    url = url,
                    topic = topic,
                    isPrivate = isPrivate,
                    numSpeakers = numSpeakers,
                    numAll = numAll,
                    numOther = numOther,
                    clubId = clubId,
                    clubName = clubName,
                    creatorUserProfileId = creatorUserProfileId,
                    hasBlockedSpeakers = hasBlockedSpeakers,
                    isExploreChannel = isExploreChannel,
                    isSocialMode = isSocialMode,
                    welcomeForUserProfile = welcomeForUserProfile,
                    users = listMapper.mapFromModelList(users)
            )
        }
    }
}