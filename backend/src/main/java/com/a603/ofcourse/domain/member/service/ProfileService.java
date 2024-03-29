package com.a603.ofcourse.domain.member.service;

import com.a603.ofcourse.domain.member.domain.Member;
import com.a603.ofcourse.domain.member.domain.Profile;
import com.a603.ofcourse.domain.member.domain.enums.Gender;
import com.a603.ofcourse.domain.member.dto.request.Preference;
import com.a603.ofcourse.domain.member.dto.request.ProfileInfoRequest;
import com.a603.ofcourse.domain.member.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final MemberService memberService;

    private static final double HALF_AS_MANY = 1.5;
    private static final double ONCE = 1.0;
    private static final double HALF = 0.5;
    /*

     */
    public boolean checkNickName(String nickName) {
        return profileRepository.existsByNickname(nickName);
    }

    /**
     * @author 손현조
     * @date 2024-03-27
     * @description 프로필 저장
     **/
    public void saveMemberProfile(Integer memberId, ProfileInfoRequest profileInfoRequest) {
        Member member = memberService.findById(memberId);
        String vector = Arrays.toString(getVector(profileInfoRequest.getPreference()));
        Profile profile = Profile.builder()
                .member(member)
                .memberVector(vector)
                .gender(Gender.valueOf(profileInfoRequest.getGender()))
                .nickname(profileInfoRequest.getNickName())
                .build();
        profileRepository.save(profile);
    }

    /**
     * @author 손현조
     * @date 2024-03-27
     * @description 콜드 스타트용 벡터값 생성
     **/
    private Double[] getVector(Preference preference) {
        Double[] vector = new Double[41];
        String[] preferenceList = {
                preference.getFirst(),
                preference.getSecond(),
                preference.getThird()};

        int cnt = 0;
        for (String pref : preferenceList) {
            int[] indexes = getIndexes(pref);
            adjustVectorChange(cnt++, vector, indexes);
        }
        return vector;
    }

    private int[] getIndexes(String preference) {
        return switch (preference) {
            case "맛" -> new int[]{0, 7, 13, 18, 23, 26, 20, 32, 35, 37};
            case "가격" -> new int[]{1, 8, 14, 19};
            case "특별" -> new int[]{2, 9, 14, 20, 24, 27, 30, 33};
            case "위생" -> new int[]{3, 7, 15, 17};
            case "분위기" -> new int[]{4, 10, 11, 16, 21, 22, 25, 28, 31, 34, 36, 38, 39, 40};
            case "편의성" -> new int[]{5, 11, 16, 22, 12, 29};
            case "친절" -> new int[]{6, 12, 17};
            default -> new int[]{};
        };
    }

    private void adjustVectorChange(int cnt, Double[] vector, int[] indexes) {
        switch (cnt) {
            case 0:
                addWeight(vector, indexes, HALF_AS_MANY);
                break;
            case 1:
                addWeight(vector, indexes, ONCE);
                break;
            case 2:
                addWeight(vector, indexes, HALF);
                break;
        }
    }

    private void addWeight(Double[] vector, int[] indexes, double weight) {
        for (int index : indexes) {
            vector[index] += weight;
        }
    }
}

