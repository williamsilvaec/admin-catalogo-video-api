package com.williamsilva.admin.catalogo.application;

public abstract class UseCase<IN, OUT> {

    public abstract OUT execute(IN input);
}
