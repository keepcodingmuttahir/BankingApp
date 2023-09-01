package com.redmath.bankingapp.user;

/*import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;*/
import com.redmath.bankingapp.balance.BalanceRepository;
import com.redmath.bankingapp.transaction.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    private final UserRepository repository;
    private final TransactionRepository transactionRepository;
    private final BalanceRepository balanceRepository;

    public UserService(UserRepository repository, TransactionRepository transactionRepository, BalanceRepository balanceRepository) {
        this.repository = repository;
        this.transactionRepository = transactionRepository;
        this.balanceRepository = balanceRepository;
    }

    public List<User> findAll() {
       return repository.findAll();
    }

    public User create(User user)
    {
        return repository.save(user);
    }

    public User findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(long id) {
        Optional<User> user = repository.findById(id);

        if (user.isPresent()) {
            repository.deleteById(id);
            balanceRepository.deleteById(id);
            int rowsDeleted = transactionRepository.deleteByUserId(id);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }
    public User update(User userToUpdate, long id) {

        Optional<User> existingNewsOptional = repository.findById(id);

        if (existingNewsOptional.isEmpty()) {
            return null;
        }
        User existingUser = existingNewsOptional.get();
        existingUser.setUserName(userToUpdate.getUserName());
        existingUser.setPassword(userToUpdate.getPassword());
        existingUser.setEmail(userToUpdate.getEmail());
        existingUser.setAddress(userToUpdate.getAddress());
        existingUser.setRole(userToUpdate.getRoles());
        repository.save(existingUser);
        return existingUser;
    }
}




















//public class UserService implements UserDetailsService
/*@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = repository.findByUserName(username);
    if( user == null)
    {
        throw new UsernameNotFoundException("This user is not found" + username);
    }
    return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(), true, true , true, true, AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRoles()));
}*/

