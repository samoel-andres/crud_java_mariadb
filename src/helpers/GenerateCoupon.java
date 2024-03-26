package helpers;

public class GenerateCoupon {
    private String[] letter = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q",
            "r", "s", "t", "u", "v", "w", "x", "y", "z" };

    private int aleatoryNum() {
        return (int) (Math.random() * 103 + 1.5);
    }

    private String aleatoryLetter() {
        return this.letter[(int) (Math.random() * this.letter.length) + 1];
    }

    public String generate(String coupon_type) {
        if (coupon_type == null) {
            coupon_type = "Generic";
        }

        return String.valueOf(coupon_type.charAt(0)).toUpperCase() + this.aleatoryNum()
                + this.aleatoryLetter().toUpperCase() + this.aleatoryLetter().toUpperCase() + this.aleatoryNum();
    }

}
