package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pedido.class)
public abstract class Pedido_ {

	public static volatile SingularAttribute<Pedido, Cliente> cliente;
	public static volatile ListAttribute<Pedido, Produto> produtos;
	public static volatile SingularAttribute<Pedido, Double> valorTotal;
	public static volatile SingularAttribute<Pedido, Long> id;

}

