package com.vinicius.admin.catalog.domain;

public class AggregateRoot<ID extends Identifier> extends Entity<ID>{

    protected AggregateRoot(final ID id) {
        super(id);
    }
}
