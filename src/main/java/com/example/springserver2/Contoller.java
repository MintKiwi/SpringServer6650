package com.example.springserver2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Contoller implements ErrorController{
    @Autowired
    SwipeDetails swipeDetails;

    @PostMapping(value ={ "/swipe/left", "/swipe/right"})
    public Object returnResult(@RequestBody SwipeDetails swp){


        String swiper = swp.getSwiper();
        String comment = swp.getComment();
        String swipee = swp.getSwipee();
        if (!swiper.matches("\\d+") || !swipee.matches("\\d+") || comment.length() != 256) {

            return new ResponseEntity<SwipeDetails>(swipeDetails,HttpStatus.BAD_REQUEST);

    } else if (!isValid(swiper, swipee)) {
            return new ResponseEntity<SwipeDetails>(swipeDetails,HttpStatus.BAD_REQUEST);
    }

        swipeDetails.setSwipee(swp.getSwipee());
        swipeDetails.setComment(swp.getComment());
        swipeDetails.setSwiper(swp.getSwiper());

        return new ResponseEntity<SwipeDetails>(swipeDetails,HttpStatus.CREATED);
    }

    protected boolean isValid(String swiper, String swipee) {
        if(swiper.length() > 5 || swipee.length() > 7) return false;
        long swiperInt = Integer.valueOf(swiper);
        long swipeeInt = Integer.valueOf(swipee);
        return (swiperInt >= 1 && swiperInt <= 5000 && swipeeInt >= 1 && swipeeInt <= 1000000);

    }

    @RequestMapping("/error")
    public Object returnError(){
        return new ResponseEntity<SwipeDetails>(swipeDetails,HttpStatus.NOT_FOUND);
    }


}
