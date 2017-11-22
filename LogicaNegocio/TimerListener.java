/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;

import java.util.EventListener;

/**
 *
 * @author Compaq-presario-cq43
 */
public interface TimerListener extends EventListener{
    public void eventoTimer(String tiempo);
}
