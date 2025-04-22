package com.ibm.bootcamp.spring.datalake;

public interface FromDto<D,M> {

    M fromDto(D dto);

}
