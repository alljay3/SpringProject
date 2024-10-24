/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Spiva.demo.exeptions;

/**
 *
 * @author miha2
 */
public class DateIsNull extends RuntimeException {
    public DateIsNull(String errorMessage) {
        super(errorMessage);
    }
}
