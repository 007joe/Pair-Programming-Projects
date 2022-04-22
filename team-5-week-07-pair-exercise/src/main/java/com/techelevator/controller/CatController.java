package com.techelevator.controller;

import com.techelevator.dao.CatCardDao;
import com.techelevator.model.CatCard;
import com.techelevator.model.CatCardNotFoundException;
import com.techelevator.services.CatFactService;
import com.techelevator.services.CatPicService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CatController {

    private CatCardDao catCardDao;
    private CatFactService catFactService;
    private CatPicService catPicService;

    public CatController(CatCardDao catCardDao, CatFactService catFactService, CatPicService catPicService) {
        this.catCardDao = catCardDao;
        this.catFactService = catFactService;
        this.catPicService = catPicService;
    }
    //controller method that provides a new, randomly created card
    //GET /api/cards/random
    //public Hotel get(@PathVariable int id) {
    //        return hotelDao.get(id);

    //get list of cat cards
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<CatCard> list() throws CatCardNotFoundException {
        return catCardDao.list();
    }
    //create card
    @RequestMapping(path = "/random", method = RequestMethod.GET)
    public CatCard randomCatCard() throws CatCardNotFoundException {
        CatCard newRandomCard = new CatCard();
        randomCatCard().setCatFact(catFactService.getFact().getText());
        randomCatCard().setImgUrl(catPicService.getPic().getFile());
        return randomCatCard();
    }


    //save card
    //ResponseCOde Created 201
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public CatCard saveCard(@RequestBody CatCard catcard) throws CatCardNotFoundException {
        catCardDao.save(catcard);
        return catcard;
    }


    //update card
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public CatCard updateCard(@Valid @RequestBody CatCard catCard, @PathVariable int id) throws CatCardNotFoundException {
         catCardDao.update(id, catCard);
         return catCard;
    }


    //delete card
    //ResponseCode NoContent
@ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteCard(@PathVariable int id) throws CatCardNotFoundException {
        catCardDao.delete(id);
}



}
