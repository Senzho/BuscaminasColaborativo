package LogicaNegocio;

public class IpAddress {
    private String address;
    private int x1;
    private int x2;
    private int x3;
    private int x4;
    
    private boolean testAddress(String address){
        boolean valid = true;
        int start = 0;
        int dotCount = 0;
        int addressLength = address.length();
        for (int i = 0; i < addressLength; i ++){
            if (address.charAt(i) == '.' || i == addressLength - 1){
                dotCount ++;
                int end = i;
                if (i == addressLength - 1){
                    end ++;
                }
                String section = address.substring(start, end);
                start = i + 1;
                try{
                    if (Integer.parseInt(section) > 255){
                        valid = false;
                    }
                }catch(NumberFormatException exception){
                    valid = false;
                    break;
                }
            }
        }
        if (dotCount < 4 || dotCount > 4 || address.endsWith(".")){
            valid = false;
        }
        return valid;
    }
    private void setValues(String address){
        int addressLength = address.length();
        int start = 0;
        int sectionCount = 0;
        for (int i = 0; i < addressLength; i ++){
            if (address.charAt(i) == '.' || i == addressLength - 1){
                int end = i;
                if (i == addressLength - 1){
                    end ++;
                }
                String section = address.substring(start, end);
                sectionCount ++;
                start = i + 1;
                switch(sectionCount){
                    case 1:
                        this.x1 = Integer.parseInt(section);
                        break;
                    case 2:
                        this.x2 = Integer.parseInt(section);
                        break;
                    case 3:
                        this.x3 = Integer.parseInt(section);
                        break;
                    case 4:
                        this.x4 = Integer.parseInt(section);
                        break;
                }
                this.address = "" + x1 + '.' + x2 + '.' + x3 + '.' + x4;
            }
        }
    }
    private void setValues(){
        this.x1 = 127;
        this.x2 = 0;
        this.x3 = 0;
        this.x4 = 1;
        this.address = "" + x1 + '.' + x2 + '.' + x3 + '.' + x4;
    }
    /**
     * Establece la dirección IP.
     * @param address, la cadena con una dirección IP.
     * @throws InvalidIpAddressException, lanzada si la dirección IP ingresada es
     *         incorrecta.
     */
    public IpAddress(String address) throws InvalidIpAddressException{
        if (this.testAddress(address) || address.equals("localhost")){
            if (address.equals("localhost")){
                this.setValues();
            }else{
                this.setValues(address);
            }
        }else{
            throw new InvalidIpAddressException("La dirección no es válida");
        }
    }
    /**
     * 
     * @return address, regresa la dirección IP.
     */
    public String getAddress() {
        return address;
    }
    /**
     * 
     * @return x1, regresa el primer valor entero de la IP.
     */
    public int getX1() {
        return x1;
    }
    /**
     * 
     * @return x2, regresa el segundo valor entero de la IP.
     */
    public int getX2() {
        return x2;
    }
    /**
     * 
     * @return x3, regresa el tercer valor entero de la IP.
     */
    public int getX3() {
        return x3;
    }
    /**
     * 
     * @return x4, regresa el cuarto valor entero de la IP.
     */
    public int getX4() {
        return x4;
    }
}
