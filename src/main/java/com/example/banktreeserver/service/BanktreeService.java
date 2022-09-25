package com.example.banktreeserver.service;

import com.example.banktreeserver.entity.Banktree;
import com.example.banktreeserver.kakaoapi.KakaoApi;
import com.example.banktreeserver.naverapi.NaverApi;
import com.example.banktreeserver.repository.BanktreeRepository;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class BanktreeService {

    @Autowired
    BanktreeRepository banktreeRepository;
    @Autowired
    KakaoApi kakaoApi;
    @Autowired
    NaverApi naverApi;

    @PostConstruct
    public void init(){
        naverApi.findBankPath();
    }
    public List<Banktree> banktreeList() {
      //test code
      //  banktreeRepository.save(new Banktree(1L));
      //  banktreeRepository.save(new Banktree(2L));
        List<Banktree> banktrees = banktreeRepository.findAll();

        return banktrees;
    }

    public float[][] findMypath(String start, String goal) throws ParseException {
        float [] startC = kakaoApi.givenLocationreturnLanLon(start);
        float [] goalC = kakaoApi.givenLocationreturnLanLon(goal);

        return new float[][]{startC,goalC};
    }
}

















