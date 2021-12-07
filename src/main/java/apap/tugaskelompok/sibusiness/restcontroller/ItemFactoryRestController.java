package apap.tugaskelompok.sibusiness.restcontroller;

import apap.tugaskelompok.sibusiness.models.ItemFactoryModel;
import apap.tugaskelompok.sibusiness.service.ItemFactoryRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/item")
public class ItemFactoryRestController {
    @Autowired
    private ItemFactoryRestService itemFactoryRestService;

    @PostMapping("/propose")
    private ResponseEntity createItemFactoryPropose (
            @Valid @RequestBody ItemFactoryModel itemFactoryModel,
            BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field."
            );
        } else {
            itemFactoryModel.setStatus(0);
            itemFactoryRestService.addItemFactory(itemFactoryModel);
            return ResponseEntity.ok("Create Propose Item success");
        }
    }
}
