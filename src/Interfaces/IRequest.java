package Interfaces;


import java.io.Serializable;

public interface IRequest extends Serializable {

    String getDestination();

    IResponse processRequest();
}
