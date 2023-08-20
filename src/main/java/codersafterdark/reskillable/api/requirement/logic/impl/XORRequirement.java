package codersafterdark.reskillable.api.requirement.logic.impl;

import codersafterdark.reskillable.api.requirement.Requirement;
import codersafterdark.reskillable.api.requirement.RequirementComparision;
import codersafterdark.reskillable.api.requirement.logic.DoubleRequirement;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class XORRequirement extends DoubleRequirement {
    public XORRequirement(Requirement left, Requirement right) {
        super(left, right);
    }

    @Override
    public boolean achievedByPlayer(ServerPlayer player) {
        return leftAchieved(player) != rightAchieved(player);
    }

    @Override
    protected String getFormat() {
        return Component.translatable("reskillable.requirements.format.xor").getString();
    }

    @Override
    public RequirementComparision matches(Requirement o) {
        if (o instanceof ORRequirement) {
            ORRequirement other = (ORRequirement) o;
            RequirementComparision left = getLeft().matches(other.getLeft());
            RequirementComparision right = getRight().matches(other.getRight());
            boolean same = left.equals(right);
            if (same && left.equals(RequirementComparision.EQUAL_TO)) {
                return RequirementComparision.EQUAL_TO;
            }

            //Check to see if they were just written in the opposite order
            RequirementComparision leftAlt = getLeft().matches(other.getRight());
            RequirementComparision rightAlt = getRight().matches(other.getLeft());
            boolean altSame = leftAlt.equals(rightAlt);
            if (altSame && leftAlt.equals(RequirementComparision.EQUAL_TO)) {
                return RequirementComparision.EQUAL_TO;
            }

            //XOR specific check

        }
        return RequirementComparision.NOT_EQUAL;
    }
}