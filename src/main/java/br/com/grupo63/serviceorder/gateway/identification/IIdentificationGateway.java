package br.com.grupo63.serviceorder.gateway.identification;

import br.com.grupo63.serviceorder.gateway.identification.dto.ClientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@FeignClient(name = "identification", url = "/identification/clients")
public interface IIdentificationGateway {

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    Optional<ClientDTO> getById(@PathVariable("id") Long orderId);

}
