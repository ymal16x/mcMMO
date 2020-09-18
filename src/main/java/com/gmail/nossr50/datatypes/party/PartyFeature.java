package com.gmail.nossr50.datatypes.party;

import com.gmail.nossr50.commands.party.PartySubCommandType;
import com.gmail.nossr50.config.Config;
import com.gmail.nossr50.locale.LocaleLoader;
import com.gmail.nossr50.util.Permissions;
import com.gmail.nossr50.util.StringUtils;
import org.bukkit.entity.Player;

public enum PartyFeature {
    CHAT,
    TELEPORT,
    ALLIANCE,
    ITEM_SHARE,
    XP_SHARE;

    public String getLocaleString() {
        return LocaleLoader.getString("Party.Feature." + StringUtils.getPrettyPartyFeatureString(this).replace(" ", ""));
    }

    public String getFeatureLockedLocaleString() {
        return LocaleLoader.getString("Ability.Generic.Template.Lock", LocaleLoader.getString("Party.Feature.Locked." + StringUtils.getPrettyPartyFeatureString(this).replace(" ", ""), Config.getInstance().getPartyFeatureUnlockLevel(this)));
    }

    public boolean hasPermission(Player player) {
        PartySubCommandType partySubCommandType;
        switch (this) {
            case CHAT:
                partySubCommandType = PartySubCommandType.CHAT;
                break;
            case TELEPORT:
                partySubCommandType = PartySubCommandType.TELEPORT;
                break;
            case ALLIANCE:
                partySubCommandType = PartySubCommandType.ALLIANCE;
                break;
            case ITEM_SHARE:
                partySubCommandType = PartySubCommandType.ITEMSHARE;
                break;
            case XP_SHARE:
                partySubCommandType = PartySubCommandType.XPSHARE;
                break;
            default:
                return false;
        }


        return Permissions.partySubcommand(player, partySubCommandType);
    }
}
