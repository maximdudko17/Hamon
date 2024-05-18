package hamon.first.budget_app.controllers;

import hamon.first.budget_app.DTO.WalletDTO;
import hamon.first.budget_app.models.User;
import hamon.first.budget_app.models.Wallet;
import hamon.first.budget_app.requests.DecreaseAmountRequest;
import hamon.first.budget_app.requests.IncreaseAmountRequest;
import hamon.first.budget_app.service.UserService;
import hamon.first.budget_app.service.WalletService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    private final ModelMapper modelMapper;

    private final WalletService walletService;

    private final UserService userService;

    @Autowired
    public WalletController(ModelMapper modelMapper, WalletService walletService, UserService userService) {
        this.modelMapper = modelMapper;
        this.walletService = walletService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createWallet(@RequestBody WalletDTO walletDTO,
                                          UsernamePasswordAuthenticationToken auth){

        Wallet wallet = convertToWallet(walletDTO);
        Optional<User> user = userService.loadUserByUsername(auth.getName());
        if (user.isPresent()) {
            wallet.setUser(user.get());
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        walletService.save(wallet);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private Wallet convertToWallet(WalletDTO walletDTO){
        return modelMapper.map(walletDTO, Wallet.class);
    }

    @PostMapping("/increase")
    public ResponseEntity<?> increaseWalletMoney(@RequestBody IncreaseAmountRequest increaseAmountRequest){
        walletService.increaseWalletAmount(increaseAmountRequest.getAmount(), increaseAmountRequest.getId());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/decrease")
    public ResponseEntity<?> decreaseWalletMoney(@RequestBody DecreaseAmountRequest decreaseAmountRequest){
        walletService.decreaseWalletAmount(decreaseAmountRequest.getAmount(), decreaseAmountRequest.getId());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
