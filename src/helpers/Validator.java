package helpers;

public class Validator {
    private double value;
    private double r;

    public String VerifyDouble(String value) {
        try {
            this.value = Double.parseDouble(value);
            this.r = (Math.round(this.value * 100.0) / 100.0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (this.value > 0) {
            return String.valueOf(this.r);
        }

        return "Err";
    }

    public String VerifyInteger(String value) {
        try {
            this.value = Double.parseDouble(value);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (this.value > 0) {
            return String.valueOf((int) this.value);
        }

        return "Err";
    }

    public String VerifyString(String value) {
        try {
            if (!value.isEmpty()) {
                return value;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "Err";
    }

}
