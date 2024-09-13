package controllers;

import java.math.BigDecimal;
import java.sql.ResultSet;

import helpers.GenerateCoupon;
import helpers.Validator;

public class Controller {
    // private String[] actions = { "users.update", "customers.update",
    // "providers.update", "stock.update",
    // "products.update", "sales.update", "coupons.update", "orders.update" };
    private String phone_number, email, street, ext_num, int_num, delegation, country, name, lastname, dni, curp,
            company_name, person, units, unit_type, units_by_unit_type, total_units, price_by_unit_type, product_name,
            size, price, list_items, cant_items, subtotal, on_account, status, comments, flag, minimum_purchase,
            maximum_purchase, expires, coupon_type, award;
    // , coupon;

    public Controller() {
    }

    public Controller(String phone_number, String email, String street, String ext_num, String int_num,
            String delegation, String country, String name, String lastname, String dni, String curp,
            String company_name, String person, String units, String unit_type, String units_by_unit_type,
            String total_units, String price_by_unit_type, String product_name, String size, String price,
            String list_items, String cant_items, String subtotal, String on_account, String status, String comments,
            String flag, String minimum_purchase, String maximum_purchase, String expires, String coupon_type,
            String award, String coupon) {
        this.phone_number = phone_number;
        this.email = email;
        this.street = street;
        this.ext_num = ext_num;
        this.int_num = int_num;
        this.delegation = delegation;
        this.country = country;
        this.name = name;
        this.lastname = lastname;
        this.dni = dni;
        this.curp = curp;
        this.company_name = company_name;
        this.person = person;
        this.units = units;
        this.unit_type = unit_type;
        this.units_by_unit_type = units_by_unit_type;
        this.total_units = total_units;
        this.price_by_unit_type = price_by_unit_type;
        this.product_name = product_name;
        this.size = size;
        this.price = price;
        this.list_items = list_items;
        this.cant_items = cant_items;
        this.subtotal = subtotal;
        this.on_account = on_account;
        this.status = status;
        this.comments = comments;
        this.flag = flag;
        this.minimum_purchase = minimum_purchase;
        this.maximum_purchase = maximum_purchase;
        this.expires = expires;
        this.coupon_type = coupon_type;
        this.award = award;
        // this.coupon = coupon;
    }

    public String newCustomer() {
        try {
            this.phone_number = new Validator().VerifyString(this.phone_number);
            this.email = new Validator().VerifyString(this.email);

            if (this.phone_number != "Err" && this.email != "Err") {
                this.street = new Validator().VerifyString(this.street);
                this.ext_num = new Validator().VerifyInteger(this.ext_num);
                this.int_num = new Validator().VerifyInteger(this.int_num);
                this.delegation = new Validator().VerifyString(this.delegation);
                this.country = new Validator().VerifyString(this.country);

                if (this.street != "Err" && this.ext_num != "Err" && this.int_num != "Err" && this.delegation != "Err"
                        && this.country != "Err") {
                    this.name = new Validator().VerifyString(this.name);
                    this.lastname = new Validator().VerifyString(this.lastname);
                    this.dni = new Validator().VerifyString(this.dni);
                    this.curp = new Validator().VerifyString(this.curp);

                    if (this.name != "Err" && this.lastname != "Err" && this.dni != "Err" && this.curp != "Err") {
                        BigDecimal contact = new ContactController(
                                this.phone_number,
                                this.email.toUpperCase())
                                .create();

                        BigDecimal direction = new DirectionController(
                                this.street.toUpperCase(),
                                Integer.parseInt(this.ext_num),
                                Integer.parseInt(this.int_num),
                                this.delegation.toUpperCase(),
                                this.country.toUpperCase())
                                .create();

                        BigDecimal customer = new CustomerController(
                                this.name.toUpperCase(),
                                this.lastname.toUpperCase(),
                                this.dni.toUpperCase(),
                                this.curp.toUpperCase(),
                                contact,
                                direction)
                                .create();

                        if (contact != null) {
                            if (direction != null) {
                                if (customer != null) {
                                    return String.valueOf(customer);
                                } else {
                                    new ContactController(
                                            null,
                                            null)
                                            .delete(contact);

                                    new DirectionController(
                                            null,
                                            0,
                                            0,
                                            null,
                                            null)
                                            .delete(direction);

                                    return "customer";
                                }
                            } else {
                                new ContactController(
                                        null,
                                        null)
                                        .delete(contact);

                                new DirectionController(
                                        null,
                                        0,
                                        0,
                                        null,
                                        null)
                                        .delete(direction);

                                return "address";
                            }
                        } else {
                            new ContactController(
                                    null,
                                    null)
                                    .delete(contact);

                            new DirectionController(
                                    null,
                                    0,
                                    0,
                                    null,
                                    null)
                                    .delete(direction);

                            return "contact";
                        }
                    } else {
                        return "customer";
                    }
                } else {
                    return "address";
                }
            } else {
                return "contact";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "general_err";
    }

    public String newUser() {
        try {
            this.phone_number = new Validator().VerifyString(this.phone_number);
            this.email = new Validator().VerifyString(this.email);

            if (this.phone_number != "Err" && this.email != "Err") {
                this.street = new Validator().VerifyString(this.street);
                this.ext_num = new Validator().VerifyInteger(this.ext_num);
                this.int_num = new Validator().VerifyInteger(this.int_num);
                this.delegation = new Validator().VerifyString(this.delegation);
                this.country = new Validator().VerifyString(this.country);

                if (this.street != "Err" && this.ext_num != "Err" && this.int_num != "Err" && this.delegation != "Err"
                        && this.country != "Err") {
                    this.name = new Validator().VerifyString(this.name);
                    this.lastname = new Validator().VerifyString(this.lastname);
                    this.dni = new Validator().VerifyString(this.dni);
                    this.curp = new Validator().VerifyString(this.curp);

                    if (this.name != "Err" && this.lastname != "Err" && this.dni != "Err" && this.curp != "Err") {
                        BigDecimal contact = new ContactController(
                                this.phone_number,
                                this.email.toUpperCase())
                                .create();

                        BigDecimal direction = new DirectionController(
                                this.street.toUpperCase(),
                                Integer.parseInt(this.ext_num),
                                Integer.parseInt(this.int_num),
                                this.delegation.toUpperCase(),
                                this.country.toUpperCase())
                                .create();

                        BigDecimal user = new UserController(
                                this.name.toUpperCase(),
                                this.lastname.toUpperCase(),
                                this.dni.toUpperCase(),
                                this.curp.toUpperCase(),
                                contact,
                                direction)
                                .create();

                        if (contact != null) {
                            if (direction != null) {
                                if (user != null) {
                                    return String.valueOf(user);
                                } else {
                                    new ContactController(
                                            null,
                                            null)
                                            .delete(contact);

                                    new DirectionController(
                                            null,
                                            0,
                                            0,
                                            null,
                                            null)
                                            .delete(direction);

                                    return "user";
                                }
                            } else {
                                new ContactController(
                                        null,
                                        null)
                                        .delete(contact);

                                new DirectionController(
                                        null,
                                        0,
                                        0,
                                        null,
                                        null)
                                        .delete(direction);

                                return "address";
                            }
                        } else {
                            new ContactController(
                                    null,
                                    null)
                                    .delete(contact);

                            new DirectionController(
                                    null,
                                    0,
                                    0,
                                    null,
                                    null)
                                    .delete(direction);

                            return "contact";
                        }
                    } else {
                        return "uuser";
                    }
                } else {
                    return "address";
                }
            } else {
                return "contact";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "general_err";
    }

    public String newProvider() {
        try {
            this.phone_number = new Validator().VerifyString(this.phone_number);
            this.email = new Validator().VerifyString(this.email);

            if (this.phone_number != "Err" && this.email != "Err") {
                this.street = new Validator().VerifyString(this.street);
                this.ext_num = new Validator().VerifyInteger(this.ext_num);
                this.int_num = new Validator().VerifyInteger(this.int_num);
                this.delegation = new Validator().VerifyString(this.delegation);
                this.country = new Validator().VerifyString(this.country);

                if (this.street != "Err" && this.ext_num != "Err" && this.int_num != "Err" && this.delegation != "Err"
                        && this.country != "Err") {
                    this.company_name = new Validator().VerifyString(this.company_name);
                    this.person = new Validator().VerifyString(this.person);

                    if (this.company_name != "Err" && this.person != "Err") {
                        BigDecimal contact = new ContactController(
                                this.phone_number,
                                this.email.toUpperCase())
                                .create();

                        BigDecimal direction = new DirectionController(
                                this.street.toUpperCase(),
                                Integer.parseInt(this.ext_num),
                                Integer.parseInt(this.int_num),
                                this.delegation.toUpperCase(),
                                this.country.toUpperCase())
                                .create();

                        BigDecimal provider = new ProviderController(
                                this.company_name.toUpperCase(),
                                this.person.toUpperCase(),
                                contact,
                                direction)
                                .create();

                        if (contact != null) {
                            if (direction != null) {
                                if (provider != null) {
                                    return String.valueOf(provider);
                                } else {
                                    new ContactController(
                                            null,
                                            null)
                                            .delete(contact);

                                    new DirectionController(
                                            null,
                                            0,
                                            0,
                                            null,
                                            null)
                                            .delete(direction);

                                    return "provider";
                                }
                            } else {
                                new ContactController(
                                        null,
                                        null)
                                        .delete(contact);

                                new DirectionController(
                                        null,
                                        0,
                                        0,
                                        null,
                                        null)
                                        .delete(direction);

                                return "address";
                            }
                        } else {
                            new ContactController(
                                    null,
                                    null)
                                    .delete(contact);

                            new DirectionController(
                                    null,
                                    0,
                                    0,
                                    null,
                                    null)
                                    .delete(direction);

                            return "contact";
                        }
                    } else {
                        return "provider";
                    }
                } else {
                    return "address";
                }
            } else {
                return "contact";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "general_err";
    }

    public String newStock(BigDecimal provider) {
        try {
            this.units = new Validator().VerifyInteger(this.units);
            this.unit_type = new Validator().VerifyString(this.unit_type);
            this.units_by_unit_type = new Validator().VerifyDouble(this.units_by_unit_type);
            this.total_units = new Validator()
                    .VerifyDouble(
                            String.valueOf(
                                    Double.parseDouble(this.units)
                                            * Double.parseDouble(this.units_by_unit_type)));
            this.price_by_unit_type = new Validator().VerifyDouble(this.price_by_unit_type);

            if (this.units != "Err" && this.unit_type != "Err" && this.units_by_unit_type != "Err"
                    && this.total_units != "Err" && this.price_by_unit_type != "Err") {

                if (provider != null) {
                    BigDecimal stock = new StockController(
                            Integer.parseInt(this.units),
                            this.unit_type.toUpperCase(),
                            Double.parseDouble(this.units_by_unit_type),
                            Double.parseDouble(this.total_units),
                            Double.parseDouble(this.price_by_unit_type),
                            provider)
                            .create();

                    if (stock != null) {
                        return String.valueOf(stock);
                    } else {
                        return "stock";
                    }
                } else {
                    return "key_provider";
                }
            } else {
                return "stock";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "general_err";
    }

    public String newProduct(BigDecimal stock) {
        try {
            this.product_name = new Validator().VerifyString(this.product_name);
            this.size = new Validator().VerifyString(this.size);
            this.price = new Validator().VerifyDouble(this.price);

            if (this.product_name != "Err" && this.size != "Err" && this.price != "Err") {
                if (stock != null) {
                    BigDecimal product = new ProductController(
                            this.product_name.toUpperCase(),
                            this.size.toUpperCase(),
                            Double.parseDouble(this.price),
                            stock)
                            .create();

                    if (product != null) {
                        return String.valueOf(product);
                    } else {
                        return "product";
                    }
                } else {
                    return "key_stock";
                }
            } else {
                return "product";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "general_err";
    }

    public String newCoupon() {
        try {
            this.minimum_purchase = new Validator().VerifyDouble(this.minimum_purchase);
            this.maximum_purchase = new Validator().VerifyDouble(this.maximum_purchase);
            this.expires = new Validator().VerifyString(this.expires);
            this.status = new Validator().VerifyString(this.status);
            this.coupon_type = new Validator().VerifyString(this.coupon_type);
            this.award = new Validator().VerifyDouble(this.award);

            if (coupon_type != "Err") {
                if (this.minimum_purchase != "Err" && this.maximum_purchase != "Err" && this.expires != "Err"
                        && this.status != "Err" && this.coupon_type != "Err" && this.award != "Err") {
                    BigDecimal coupon_n = new CouponController(
                            Double.parseDouble(this.minimum_purchase),
                            Double.parseDouble(this.maximum_purchase),
                            this.expires,
                            this.status.toUpperCase(),
                            this.coupon_type.toUpperCase(),
                            Double.parseDouble(this.award),
                            new GenerateCoupon().generate(this.coupon_type).toUpperCase())
                            .create();

                    if (coupon_n != null) {
                        return String.valueOf(coupon_n);
                    } else {
                        return "coupon";
                    }
                } else {
                    return "coupon";
                }
            } else {
                return "coupon_generation_err";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "general_err";
    }

    public String newOrder(BigDecimal user, BigDecimal customer, BigDecimal coupon_key) {
        try {
            this.list_items = new Validator().VerifyString(this.list_items);
            this.cant_items = new Validator().VerifyDouble(this.cant_items);
            this.subtotal = new Validator().VerifyDouble(this.subtotal);
            this.on_account = new Validator().VerifyDouble(this.on_account);
            this.status = new Validator().VerifyString(this.status);

            if (this.list_items != "Err" && this.cant_items != "Err" && this.subtotal != "Err"
                    && this.on_account != "Err" && this.status != "Err") {

                if (user != null) {
                    if (customer != null) {
                        BigDecimal order = new OrderController(
                                user,
                                customer,
                                coupon_key,
                                this.list_items.toUpperCase(),
                                Double.parseDouble(this.cant_items),
                                Double.parseDouble(this.on_account),
                                Double.parseDouble(this.subtotal),
                                this.status.toUpperCase(),
                                this.comments.toUpperCase(),
                                this.flag.toUpperCase(),
                                null)
                                .create();

                        if (order != null) {
                            return String.valueOf(order);
                        } else {
                            return "order";
                        }
                    } else {
                        return "key_customer";
                    }
                } else {
                    return "key_user";
                }
            } else {
                return "order";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "general_err";
    }

    public String newSale(BigDecimal user, BigDecimal customer, BigDecimal coupon) {
        try {
            this.list_items = new Validator().VerifyString(this.list_items);
            this.cant_items = new Validator().VerifyDouble(this.cant_items);
            this.subtotal = new Validator().VerifyDouble(this.subtotal);

            if (this.list_items != "Err" && this.cant_items != "Err" && this.subtotal != "Err") {

                if (user != null) {
                    if (customer != null) {
                        BigDecimal sale = new SaleController(
                                user,
                                customer,
                                coupon,
                                this.list_items.toUpperCase(),
                                Double.parseDouble(this.cant_items),
                                Double.parseDouble(this.subtotal),
                                this.flag.toUpperCase(),
                                null)
                                .create();

                        if (sale != null) {
                            return String.valueOf(sale);
                        } else {
                            return "sale";
                        }
                    } else {
                        return "key_customer";
                    }
                } else {
                    return "key_user";
                }
            } else {
                return "sale";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "general_err";
    }

    public ResultSet readProducts(String value, String comingFrom) {
        try {
            String validation = new Validator().VerifyInteger(value);
            String by = "all";
            if (comingFrom != null) {
                if (comingFrom.equals("providers")) {
                    by = "PID";
                }
            } else {
                if (validation == "Err" && !value.isEmpty()) {
                    by = "product-name";
                } else if (validation.equals(value)) {
                    by = "product-key";
                }
            }

            return new ProductController(this.product_name, this.size, 0, new BigDecimal(0))
                    .read(by, value.toUpperCase());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ResultSet readCustomers(String value, String CID) {
        try {
            String validation = new Validator().VerifyString(value.toUpperCase());
            String by = "all";

            if (CID.isEmpty()) {
                if (validation != "Err" && !value.isEmpty()) {
                    by = "find";
                } else {
                    by = "all";
                }
            } else {
                validation = new Validator().VerifyInteger(CID);

                if (validation != "Err" && !CID.isEmpty()) {
                    by = "CID";
                    value = CID;
                }
            }

            return new CustomerController(this.name, this.lastname, this.dni, this.curp, new BigDecimal(0),
                    new BigDecimal(0))
                    .read(by, value.toUpperCase());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ResultSet readUsers(String value, String UID) {
        try {
            String validation = new Validator().VerifyString(value.toUpperCase());
            String by = "all";

            if (UID.isEmpty()) {
                if (validation != "Err" && !value.isEmpty()) {
                    by = "find";
                } else {
                    by = "all";
                }
            } else {
                validation = new Validator().VerifyInteger(UID);

                if (validation != "Err" && !UID.isEmpty()) {
                    by = "UID";
                    value = UID;
                }
            }

            return new UserController(this.name, this.lastname, this.dni, this.curp, new BigDecimal(0),
                    new BigDecimal(0))
                    .read(by, value.toUpperCase());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ResultSet readProviders(String value, String PID) {
        try {
            String validation = new Validator().VerifyString(value.toUpperCase());
            String by = "all";

            if (PID.isEmpty()) {
                if (validation != "Err" && !value.isEmpty()) {
                    by = "find";
                } else {
                    by = "all";
                }
            } else {
                validation = new Validator().VerifyInteger(PID);

                if (validation != "Err" && !PID.isEmpty()) {
                    by = "PID";
                    value = PID;
                }
            }

            return new ProviderController(this.company_name, this.person, new BigDecimal(0), new BigDecimal(0))
                    .read(by, value.toUpperCase());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ResultSet readStock(String value, String PRID) {
        try {
            String validation = new Validator().VerifyString(value.toUpperCase());
            String by = "all";

            if (PRID.isEmpty()) {
                if (validation != "Err" && !value.isEmpty()) {
                    by = "find";
                } else {
                    by = "all";
                }
            } else {
                validation = new Validator().VerifyInteger(PRID);

                if (validation != "Err" && !PRID.isEmpty()) {
                    by = "PRID";
                    value = PRID;
                }
            }

            return new StockController(0, null, 0, 0, 0, new BigDecimal(0))
                    .read(by, value.toUpperCase());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

}
