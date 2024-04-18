package com.quetion.service;

import com.quetion.dto.ProductPricesDTO;
import com.quetion.model.UserOrderDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 *     In given question there are 3 offers as per requirement
 * for all three i created separate separate APIs respectively offer-1, offer-2, offer-3
 *
 */
@Service
public class SaleService {

     /*
     for first offer there is for discount i am using here 2 pointer approch for buying product and
     getting discount offer

     1. first rule ->
     Discount maximization for Customers. The customer’s objective is to create a pair of
      2 items so that the discount is maximized.

      2. second rule ->
      Customers can buy one product and get another product for free as long as the
price of the product is less than the price of the corresponding product in pairs.


3 rule is ->
Customers can buy two products and get two products for free as long as the
price of the product is less than the price of the first product.
*/



    public UserOrderDetails purchageProductWithOffer2(ProductPricesDTO productPricesDTO) {
        UserOrderDetails userOrderDetails = new UserOrderDetails();
        List<Integer> productPrices = productPricesDTO.getAllProductPrices();
        List<Integer> discountedItems = new ArrayList<>();
        List<Integer> payableItems = new ArrayList<>();
        /*
        here buyPointer for buying the product
        and discount pointer for discounting the product

        here one thing is common given product list in sorted order
        and then last product is higher cost that is assign as a buyPointer value here is productPrices.size() - 1
        for discount pointer just before that( assumption)
        */
        int buyPointer = productPrices.size() - 1;
        int discountPointer = buyPointer;
        int sizearray = productPrices.size();
        /*
         this is tracking array of iteration of product price list
         => for checking is that element counted/compared or not
          */
        boolean[] tracking = new boolean[sizearray];
/*
        here iterate productPrices list which is chosen by customer
        and iterate till discount and buy pointer


        */
        while (buyPointer >= 0 && discountPointer >= 0) {
            /*
            if both pointer same then decrease discount pointer
            */
            if (buyPointer == discountPointer) {
                discountPointer--;
            }
            /*
            if at any point discount pointer goes negative then break the loop and come out from that
            */
            if (discountPointer < 0) {
                break;
            }
            /*
            Here main thing is => check price at discount pointer and buy pointer
            IF SAME THEN DECREASE THE DISCOUNT POINTER
            */
            while (discountPointer >= 0 && productPrices.get(buyPointer) == productPrices.get(discountPointer)) {
                discountPointer--;
            }
            /*
            HERE CHECK DISCOUNT POINTER IS >=0 THEN
            ASSIGN PRICES IN TO RESPECTIVE LIST
            such as discount pointer will store in discount list
            and buy pointer will store in buy list as well
            AND ALSO MAKE TRUE FOR THAT INDEX WHERE YOU VALUE INSERTED IN DISCOUNT-LIST OR
            PAYABLE-LIST
            */
            if (discountPointer >= 0) {
                discountedItems.add(productPrices.get(discountPointer));
                payableItems.add(productPrices.get(buyPointer));
                tracking[buyPointer] = true;
                tracking[discountPointer] = true;

            }
            /*
            Here using the tracking array for decreasing the buy pointer on the behalf of tracking value

            */
            while (buyPointer >= 0 && tracking[buyPointer]) {
                buyPointer--;
            }
            discountPointer--;

        }
        /*
        Here some buyPointer miss above now inserting in buyList
        */
        for (Boolean aBoolean : tracking) {
            if (!aBoolean) {
                payableItems.add(productPrices.get(buyPointer));
            }
        }
/*
        Here inserting data userdetails and setting all data with respective lists

        */
        userOrderDetails.setAllProductPrices(productPricesDTO.getAllProductPrices());
        userOrderDetails.setPayablePrice(payableItems);
        userOrderDetails.setDiscountPrice(discountedItems);
        return userOrderDetails;

    }


}
