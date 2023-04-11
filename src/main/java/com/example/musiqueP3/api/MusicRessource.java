package com.example.musiqueP3.api;

import com.example.musiqueP3.entities.Music;
import com.example.musiqueP3.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Controller
public class MusicRessource {
    @Autowired
    private MusicRepository musicRepository;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("apiList")
    public @ResponseBody List<Music> list() {return (List<Music>) musicRepository.list();}

    @GetMapping("api/{id}")
    @ResponseBody
    public Optional<Music> getId(@PathVariable("id") String number) {return Optional.ofNullable(musicRepository.view(number));}
}

