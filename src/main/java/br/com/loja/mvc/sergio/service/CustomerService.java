package br.com.loja.mvc.sergio.service;

import br.com.loja.mvc.sergio.dto.CustomerData;
import br.com.loja.mvc.sergio.model.Cliente;
import br.com.loja.mvc.sergio.model.Venda;
import br.com.loja.mvc.sergio.repository.ClienteRepository;
import br.com.loja.mvc.sergio.repository.ParcelaRepository;
import br.com.loja.mvc.sergio.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    ParcelaRepository parcelaRepository;

    public List<Cliente> getAllCustomers(){
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getCustomerById(Long id){
        return clienteRepository.findById(id);
    }

    public void saveIfSomethingUpdated(Cliente customer) {
        Cliente oldCustomer = (clienteRepository.findById(customer.getId())).get();

        if(somethingUpdated(customer, oldCustomer)){
            if(nameChanged(customer, oldCustomer))
                oldCustomer.setNomeCliente(customer.getNomeCliente());

            if(stateChanged(customer, oldCustomer))
                oldCustomer.setEstado(customer.getEstado());

            if (cityChanged(customer, oldCustomer))
                oldCustomer.setCidade(customer.getCidade());

            clienteRepository.save(oldCustomer);
        }
        //TODO GUSTAVO quando for trabalhar com exceptions, criar uma exceção para quando não tiver nada para atualizar.
        //TODO GUSTAVO uma lógica para o problema acima é só enviar uma mensagem avisando que nada foi atualizado.
        else
            return; //"Nada foi atualizado";
    }

    public void deleteInstallmentsSalesAndCustomer(Long id) {
        //TODO GUSTAVO testar se quando customer vier vazio, provavelmente vai dar erro.
        Cliente customer = (clienteRepository.findById(id)).get();
        List<Venda> sales = vendaRepository.findSalesByIdClient(customer.getId());

        sales.forEach(sale -> parcelaRepository.deleteAll(parcelaRepository.findInstallmentsBySaleId(sale.getId())));
        vendaRepository.deleteAll(sales);
        clienteRepository.delete(customer);
    }

    public void createCustomer(CustomerData customerData) {
        Cliente customer = new Cliente();
        customer.setCidade(customerData.getCidade());
        customer.setEstado(customerData.getEstado());
        customer.setNomeCliente(customerData.getNomeCliente());
        customer.setSigla(customerData.getSigla());

        clienteRepository.save(customer);
    }

    private boolean somethingUpdated(Cliente customer, Cliente oldCustomer){
        return !oldCustomer.getNomeCliente().equals(customer.getNomeCliente())
                || !oldCustomer.getEstado().equals(customer.getEstado())
                || !oldCustomer.getCidade().equals(customer.getCidade());
    }

    private boolean nameChanged(Cliente customer, Cliente oldCustomer) {
        return !oldCustomer.getNomeCliente().equals(customer.getNomeCliente());
    }

    private boolean stateChanged(Cliente customer, Cliente oldCustomer) {
        return !oldCustomer.getEstado().equals(customer.getEstado());
    }

    private boolean cityChanged(Cliente customer, Cliente oldCustomer) {
        return !oldCustomer.getCidade().equals(customer.getCidade());
    }
}
