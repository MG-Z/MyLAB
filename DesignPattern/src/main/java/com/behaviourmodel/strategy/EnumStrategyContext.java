package com.behaviourmodel.strategy;

/**
 * func desc:
 * 枚举实现策略
 */
public class EnumStrategyContext {

    public static void main(String[] args) {
        EStrategy strategy = EStrategy.getStrategy("+");
        strategy.execute(1, 2);
        strategy = EStrategy.getStrategy("-");
        strategy.execute(2, 1);
    }


    public enum EStrategy {
        ADD("+") {
            @Override
            public int execute(int a, int b) {
                System.out.println("a+b=" + (a + b));
                return a + b;
            }
        },
        SUB("-") {
            @Override
            public int execute(int a, int b) {
                System.out.println("a-b=" + (a - b));
                return a - b;
            }
        },;
        private String symbol;

        EStrategy(String symbol) {
            this.symbol = symbol;
        }

        public static EStrategy getStrategy(String symbol) {
            for (EStrategy strategy : EStrategy.values()) {
                if (strategy.symbol.equalsIgnoreCase(symbol)) {
                    return strategy;
                }
            }
            throw new IllegalArgumentException("error argument :" + symbol);
        }

        public abstract int execute(int a, int b);
    }
}
