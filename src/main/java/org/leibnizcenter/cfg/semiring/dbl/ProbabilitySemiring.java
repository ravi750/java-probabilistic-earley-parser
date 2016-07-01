package org.leibnizcenter.cfg.semiring.dbl;


import org.leibnizcenter.cfg.semiring.Property;

import java.util.EnumSet;

/**
 * Probability semiring implementation.
 */
public class ProbabilitySemiring implements DblSemiring {
    private static final EnumSet<Property> properties = EnumSet.of(
            Property.LeftSemiring,
            Property.RightSemiring,
            Property.Commutative
    );

    @Override
    public double plus(double w1, double w2) {
        if (!member(w1) || !member(w2)) return Double.NEGATIVE_INFINITY;
        return w1 + w2;
    }

    @Override
    public double times(double w1, double w2) {
        if (!member(w1) || !member(w2)) return Double.NEGATIVE_INFINITY;
        return w1 * w2;
    }

    @Override
    public double zero() {
        return 0.;
    }

    @Override
    public double one() {
        return 1.;
    }

    @Override
    public boolean member(double candidate) {
        return !Double.isNaN(candidate) // not a NaN,
                && (candidate >= 0.0); // and positive
    }

    @Override
    public EnumSet<Property> properties() {
        return properties;
    }
}
