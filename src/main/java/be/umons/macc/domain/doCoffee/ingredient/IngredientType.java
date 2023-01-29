package be.umons.macc.domain.doCoffee.ingredient;

public enum IngredientType {

    COFFEE {
        @Override
        public Drink preparation() {
            return new Coffee(new Coffee(new Coffee(new Liquid(new Liquid(new Cup())))));
        }
    },
    CAPPUCCINO {
        @Override
        public Drink preparation() {
            return new Coffee(new Coffee(new Milk(new Chocolate(new Chocolate(new Liquid(new Liquid(new Cup())))))));
        }
    },
    ESPRESSO {
        @Override
        public Drink preparation() {
            return new Coffee(new Coffee(new Coffee(new Coffee(new Coffee(new Liquid(new Cup()))))));
        }
    },
    ESPRESSO_MACCHIATO {
        @Override
        public Drink preparation() {
            return new Coffee(new Coffee(new Coffee(new Coffee(new Milk(new Chocolate(new Liquid(new Cup())))))));
        }
    },
    MACCHIATO {
        @Override
        public Drink preparation() {
            return new Coffee(new Coffee(new Coffee(new Milk(new Chocolate(new Liquid(new Liquid(new Cup())))))));
        }
    },
    COFFEE_MILK {
        @Override
        public Drink preparation() {
            return new Coffee(new Coffee(new Coffee(new Coffee(new Milk(new Milk(new Liquid(new Liquid(new Cup()))))))));
        }
    },
    MILK {
        @Override
        public Drink preparation() {
            return new Milk(new Milk(new Milk(new Milk(new Liquid(new Liquid(new Cup()))))));
        }
    };

    public abstract Drink preparation();
}
