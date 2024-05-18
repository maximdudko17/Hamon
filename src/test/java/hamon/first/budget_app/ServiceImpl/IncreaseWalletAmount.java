package hamon.first.budget_app.ServiceImpl;

import hamon.first.budget_app.models.Wallet;
import hamon.first.budget_app.repositories.WalletRepository;
import hamon.first.budget_app.service.WalletService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class IncreaseWalletAmount {
    @Mock
    private WalletRepository walletRepository;
    @InjectMocks
    private WalletService walletService;

    @Test
    void exceptionIncreaseNull(){
        Wallet walletId2 = new Wallet(100);
        Wallet walletId5 = new Wallet(2000);
        Mockito.when(walletRepository.findById(3)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> walletService.increaseWalletAmount(10,3));
    }

    @Test
    void exceptionIncreaseTrue(){
        Wallet walletId2 = new Wallet(100);
        Wallet walletId5 = new Wallet(2000);
        Mockito.when(walletRepository.findById(5)).thenReturn(Optional.of(walletId5));
        walletService.increaseWalletAmount(3000,5);
        Assertions.assertEquals(walletId5.getMoney(), 5000);
    }
}
