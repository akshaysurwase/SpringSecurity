package com.security.BankBackend.Config;

//@Service
public class EasyBankUserDetails /*implements UserDetailsService*/ {

   /* @Autowired
    CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String userName=null,password=null;
        List<GrantedAuthority> authorities= null;

        List<Customer> customer= customerRepository.findByEmail(username);
        if(customer.size()==0){
            throw new  UsernameNotFoundException("User Details Not Found for the User " + username);
        }else{
            userName=customer.get(0).getEmail();
            password=customer.get(0).getPwd();
            authorities=new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(customer.get(0).getRole()));
        }
        return new User(userName,password,authorities);
    }*/
}
