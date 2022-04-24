package org.astemir.magiccraft.magic;


import org.astemir.magiccraft.config.ConfigurableSpell;

public abstract class PassiveQuirkSpell extends QuirkSpell implements IPassiveSpell,ISecretSpell{

    public PassiveQuirkSpell(ConfigurableSpell configurable) {
        super(configurable);
    }

}
