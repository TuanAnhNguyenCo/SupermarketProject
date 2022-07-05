package com.example.demo.Controller;

import com.example.demo.entity.OrderProduct;
import com.example.demo.service.IOrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value = "api/v1/order")
@CrossOrigin("*")
public class OrderController {
    @Autowired
    IOrderProductService productService;
    @Autowired
    IOrderProductService orderProductService;

    @GetMapping("/{id}")
    public ResponseEntity<?> GetOrderProduct(@PathVariable("id") int id)
    {
        OrderProduct orderProduct = orderProductService.GetOrder(id);
        if (orderProduct!=null)
            return new ResponseEntity<>(orderProduct, HttpStatus.OK);
        else
            return new ResponseEntity<>("Fail", HttpStatus.BAD_REQUEST);
    }

    // Create order
    @GetMapping("/create")
    public ResponseEntity<?> CreateProduct()
    {
        boolean status = productService.CreateOrder();
        if (status)
            return new ResponseEntity<>("Sucess", HttpStatus.OK);
        else
            return new ResponseEntity<>("Fail", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/verify")
    public ResponseEntity<?> UpdateOrderStatusByEmployee(@RequestParam int id,@RequestParam String orderstatus)
    {
        boolean status = orderProductService.UpdateOrderStatus(id,orderstatus);
        if (status)
            return new ResponseEntity<>("Sucess", HttpStatus.OK);
        else
            return new ResponseEntity<>("Fail", HttpStatus.BAD_REQUEST);

    }


    @PutMapping("/update")
    public ResponseEntity<?> UpdateOrder(@RequestParam int id,
                                         @RequestParam boolean payment_status ,@RequestParam String address)
    {
        boolean status = productService.UpdateOrder(id,payment_status,address);
        if (status)
            return new ResponseEntity<>("Sucess", HttpStatus.OK);
        else
            return new ResponseEntity<>("Fail", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> DeleteOrder(@PathVariable("id") int id)
    {
        boolean status = orderProductService.DeleteOrder(id);
        if (status)
            return new ResponseEntity<>("Sucess", HttpStatus.OK);
        else
            return new ResponseEntity<>("Fail", HttpStatus.BAD_REQUEST);
    }



}
