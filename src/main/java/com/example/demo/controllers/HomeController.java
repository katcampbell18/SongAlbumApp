package com.example.demo.controllers;

import com.example.demo.beans.Album;
import com.example.demo.beans.Song;
import com.example.demo.beans.User;
import com.example.demo.repositories.AlbumRepository;
import com.example.demo.repositories.SongRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

  @Autowired
  AlbumRepository albumRepository;
  @Autowired
  SongRepository songRepository;

  @RequestMapping(value = "/list")
  public String songList(Model model){
    model.addAttribute("songs", songRepository.findAll());
    model.addAttribute("albums", albumRepository.findAll());
    return "list";
  }

  @RequestMapping(value = "/add_album", method = RequestMethod.GET)
  public String albumForm(Model model){
    model.addAttribute("album", new Album());
    return "albumform";
  }

  @RequestMapping(value = "/process_album", method = RequestMethod.POST)
  public String  processAlbum(@ModelAttribute Album album){
    albumRepository.save(album);
    return "redirect:/";
  }

  @RequestMapping(value = "/add_song", method = RequestMethod.GET)
  public String songForm(Model model){
    model.addAttribute("albums", albumRepository.findAll());
    model.addAttribute("song", new Song());
    return "songform";
  }

  @RequestMapping(value = "/process_song", method = RequestMethod.POST)
  public String processSong(@ModelAttribute Song song){
    songRepository.save(song);
    return "redirect:/list";
  }

  @RequestMapping("/detail_song/{id}")
  public String showSong(@PathVariable("id") long id, Model model){
    model.addAttribute("song", songRepository.findById(id).get());
    return "show";
  }

  @RequestMapping("/update_song/{id}")
  public String updateSong(@PathVariable("id") long id, Model model){
    model.addAttribute("song", songRepository.findById(id).get());
    model.addAttribute("albums", albumRepository.findAll());
    return "songform";
  }

  @RequestMapping("delete_song/{id}")
  public String delSong(@PathVariable("id") long id){
    songRepository.deleteById(id);
    return "redirect:/list";
  }
}
