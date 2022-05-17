package guru.sfg.beer.order.service.services.beer;

import guru.sfg.beer.order.service.web.model.BeerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@Service
@ConfigurationProperties(value = "beer.order", ignoreUnknownFields = false)
public class BeerServiceImpl implements BeerService {
    private String beerServiceHost;
    private RestTemplate restTemplate;
    private final String BEER_BY_ID_PATH = "api/v1/beer/";
    private final String BEER_BY_UPC_PATH = "api/v1/beerUpc/";

    public BeerServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Optional<BeerDto> getBeerById(UUID uuid){
        return Optional.of(restTemplate.getForObject(beerServiceHost + BEER_BY_ID_PATH + uuid.toString(), BeerDto.class));
    }

    @Override
    public Optional<BeerDto> getBeerByUpc(String upc) {
        return Optional.of(restTemplate.getForObject(beerServiceHost + BEER_BY_UPC_PATH + upc, BeerDto.class));
    }

    public void setBeerServiceHost(String beerServiceHost) {
        this.beerServiceHost = beerServiceHost;
    }
}
