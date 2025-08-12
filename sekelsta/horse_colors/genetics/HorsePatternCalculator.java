package sekelsta.horse_colors.genetics;

import sekelsta.horse_colors.renderer.*;
import java.util.*;

public class HorsePatternCalculator
{
    public static boolean hasPigmentInEars(final HorseGenome horse) {
        return getPreviousFaceWhiteLevel(horse) <= 18;
    }
    
    public static int getPreviousFaceWhiteLevel(final HorseGenome horse) {
        int white = -2;
        if (horse.hasAllele("white_suppression", 1)) {
            white -= 4;
        }
        white += horse.countAlleles("KIT", 1);
        white += horse.countAlleles("KIT", 2);
        white += 2 * horse.countAlleles("KIT", 3);
        white += 2 * horse.countAlleles("KIT", 4);
        white += 3 * horse.countAlleles("KIT", 5);
        white += 3 * horse.countAlleles("KIT", 6);
        white += 3 * horse.countW20();
        white += 4 * horse.countAlleles("KIT", 9);
        white += 6 * horse.countAlleles("MITF", 0);
        white += 9 * horse.countAlleles("MITF", 1);
        white += 8 * horse.countAlleles("MITF", 2);
        white += 7 * horse.countAlleles("PAX3", 1);
        white += 8 * horse.countAlleles("PAX3", 2);
        white += 3 * horse.countAlleles("white_star", 1);
        white += horse.countAlleles("white_forelegs", 1);
        white += horse.countAlleles("white_hindlegs", 1);
        if (horse.hasMC1RWhiteBoost()) {
            white += 2;
        }
        return white;
    }
    
    public static TextureLayer getPreviousFaceMarking(final HorseGenome horse) {
        final int white = getPreviousFaceWhiteLevel(horse);
        final int random = HorseColorCalculator.randSource.getVal("face_white", horse.getChromosome("random"));
        final int r = random % 30;
        final int faceIndex = white + r;
        final int maxVariants = 30;
        final int variant = Math.min(faceIndex, maxVariants);
        final TextureLayer layer = new TextureLayer();
        if (variant == 0) {
            layer.name = HorseColorCalculator.fixPath("face/blaze");
        }
        else {
            layer.name = HorseColorCalculator.fixPath("face/blaze" + variant);
        }
        return layer;
    }
    
    public static void addFaceMarkings(final HorseGenome horse, final List<TextureLayer> textureLayers) {
        textureLayers.add(getPreviousFaceMarking(horse));
    }
    
    public static String[] getLegMarkings(final HorseGenome horse) {
        final WhiteBoost whiteBoost = new WhiteBoost(horse);
        final String[] legs = new String[4];
        int random = HorseColorCalculator.randSource.getVal("leg_white", horse.getChromosome("random"));
        random = random << 1 >>> 5;
        for (int i = 0; i < 4; ++i) {
            final int r = random & 0x8;
            random >>>= 3;
            int w = whiteBoost.getForelegs();
            if (i >= 2) {
                w = whiteBoost.getHindlegs();
            }
            if (w > -2) {
                w += whiteBoost.conditional;
            }
            if (w < 0) {
                legs[i] = null;
            }
            else {
                legs[i] = HorseColorCalculator.fixPath("socks/" + String.valueOf(i) + "_" + String.valueOf(Math.min(11, w / 2 + r)));
            }
        }
        return legs;
    }
    
    public static TextureLayer getPinto(final HorseGenome horse) {
        final TextureLayer layer = new TextureLayer();
        if (horse.isWhite()) {
            layer.name = HorseColorCalculator.fixPath("pinto/white");
            return layer;
        }
        final String folder = "pinto/";
        if (horse.isTobiano()) {
            final int rnd = HorseColorCalculator.randSource.getVal("pinto_tobiano", horse.getChromosome("random"));
            final int idx = rnd % 9;
            if (idx == 0) {
                layer.name = HorseColorCalculator.fixPath(folder + "tobiano");
            }
            else {
                layer.name = HorseColorCalculator.fixPath(folder + "tobiano" + idx);
            }
        }
        else if (horse.hasAllele("frame", 1)) {
            if (horse.isHomozygous("MITF", 0)) {
                layer.name = HorseColorCalculator.fixPath(folder + "medicine_hat");
            }
            else {
                layer.name = HorseColorCalculator.fixPath(folder + "war_shield");
            }
            final int variants = 8;
            final int rnd2 = HorseColorCalculator.randSource.getVal("pinto_frame", horse.getChromosome("random"));
            final int idx2 = rnd2 % variants;
            if (idx2 == 0) {
                layer.name = HorseColorCalculator.fixPath(folder + "frame");
            }
            else {
                layer.name = HorseColorCalculator.fixPath(folder + "frame_" + idx2);
            }
        }
        else if (horse.isHomozygous("MITF", 0)) {
            final int rnd = HorseColorCalculator.randSource.getVal("pinto_splash", horse.getChromosome("random"));
            final int idx = rnd % 3;
            if (idx == 0) {
                layer.name = HorseColorCalculator.fixPath(folder + "splash");
            }
            else {
                layer.name = HorseColorCalculator.fixPath(folder + "splash" + idx);
            }
        }
        else if (horse.hasAllele("KIT", 12)) {
            final int rnd = HorseColorCalculator.randSource.getVal("pinto_sabino", horse.getChromosome("random"));
            final int idx = rnd % 4;
            if (idx == 0) {
                layer.name = HorseColorCalculator.fixPath(folder + "sabino");
            }
            else {
                layer.name = HorseColorCalculator.fixPath(folder + "sabino" + idx);
            }
        }
        return layer;
    }
    
    public static void addLeopard(final HorseGenome horse, final List<TextureLayer> textureLayers) {
        if (!horse.hasAllele("leopard", 1)) {
            return;
        }
        final TextureLayer hooves = new TextureLayer();
        hooves.name = HorseColorCalculator.fixPath(horse.isHomozygous("leopard", 1) ? "leopard/lplp_features" : "leopard/lp_features");
        textureLayers.add(hooves);
        final int random = HorseColorCalculator.randSource.getVal("leopard", horse.getChromosome("random"));
        final int patn = random % 12;
        final TextureLayer spots = new TextureLayer();
        spots.name = HorseColorCalculator.fixPath(selectLeopardSpotTexture(horse, patn));
        textureLayers.add(spots);
    }
    
    private static String selectLeopardSpotTexture(final HorseGenome horse, final int patn) {
        if (patn >= 0 && patn <= 6) {
            return "leopard/blanket" + (patn + 1);
        }
        switch (patn) {
            case 7: {
                return "leopard/leopard_0";
            }
            case 8: {
                return "leopard/leopard_1";
            }
            case 9: {
                return "leopard/leopard_2";
            }
            case 10: {
                return "leopard/leopard_3";
            }
            case 11: {
                return "leopard/leopard_4";
            }
            default: {
                if (horse.hasAllele("white_suppression", 1)) {
                    return "leopard/leopard_large";
                }
                if (horse.hasAllele("marble", 1)) {
                    return "leopard/leopard_marble";
                }
                return "leopard/leopard";
            }
        }
    }
    
    public static class WhiteBoost
    {
        public int general;
        public int conditional;
        public int leg;
        public int face;
        public int foreleg;
        public int hindleg;
        public int forehead;
        public int noseBridge;
        public int muzzle;
        public int blanket;
        
        public WhiteBoost(final HorseGenome horse) {
            this.general = 0;
            this.conditional = 0;
            this.leg = 0;
            this.face = 0;
            this.foreleg = 0;
            this.hindleg = 0;
            this.forehead = 0;
            this.noseBridge = 0;
            this.muzzle = 0;
            this.blanket = 0;
            this.foreleg = 2 * horse.countAlleles("white_forelegs", 1);
            this.hindleg = 2 * horse.countAlleles("white_hindlegs", 1);
            this.forehead += horse.countAlleles("white_star", 1);
            this.setOldLegWhite(horse);
            if (horse.hasMC1RWhiteBoost()) {
                this.conditional += 2;
            }
            this.setGeneralWhite(horse);
            this.face = getOldFaceWhiteLevel(horse);
        }
        
        private void setGeneralWhite(final HorseGenome horse) {
            if (horse.hasAllele("white_suppression", 1)) {
                this.general -= 4;
            }
            this.general += horse.countAlleles("KIT", 1);
            this.general += horse.countAlleles("KIT", 2);
            this.general += 2 * horse.countAlleles("KIT", 3);
            this.general += 2 * horse.countAlleles("KIT", 4);
            this.general += 3 * horse.countAlleles("KIT", 5);
            this.general += 3 * horse.countAlleles("KIT", 6);
            this.general += 3 * horse.countW20();
            this.general += 4 * horse.countAlleles("KIT", 9);
            this.general += 2 * horse.countAlleles("MITF", 0);
            this.general += 6 * horse.countAlleles("MITF", 1);
            this.general += 2 * horse.countAlleles("MITF", 2);
            this.general += 2 * horse.countAlleles("PAX3", 1);
            this.general += 3 * horse.countAlleles("PAX3", 2);
            if (horse.hasAllele("white_star", 1)) {
                ++this.general;
            }
        }
        
        public static int getOldFaceWhiteLevel(final HorseGenome horse) {
            int white = -2;
            white += 6 * horse.countAlleles("MITF", 0);
            white += 9 * horse.countAlleles("MITF", 1);
            white += 8 * horse.countAlleles("MITF", 2);
            white += 7 * horse.countAlleles("PAX3", 1);
            white += 8 * horse.countAlleles("PAX3", 2);
            white += horse.countAlleles("white_forelegs", 1);
            white += horse.countAlleles("white_hindlegs", 1);
            if (horse.hasMC1RWhiteBoost()) {
                white += 2;
            }
            return white;
        }
        
        private void setOldLegWhite(final HorseGenome horse) {
            int white = -3;
            white += 1 * horse.countAlleles("KIT", 2);
            white += 1 * horse.countAlleles("KIT", 3);
            white += 2 * horse.countAlleles("KIT", 4);
            white += 2 * horse.countAlleles("KIT", 5);
            white += 3 * horse.countAlleles("KIT", 6);
            white += 4 * horse.countW20();
            white += 4 * horse.countAlleles("KIT", 9);
            this.leg = white;
        }
        
        public void setBlanketWhite(final HorseGenome horse) {
            if (horse.hasAllele("leopard_suppression", 1)) {
                this.blanket -= 1 + horse.countAlleles("PATN1", 1);
            }
            if (horse.isHomozygous("leopard_suppression2", 1)) {
                --this.blanket;
            }
            if (horse.hasAllele("PATN_boost1", 1)) {
                ++this.blanket;
            }
            if (horse.isHomozygous("PATN_boost2", 1)) {
                ++this.blanket;
            }
        }
        
        public int getForelegs() {
            return this.general + this.leg + this.foreleg;
        }
        
        public int getHindlegs() {
            return this.general + this.leg + this.hindleg;
        }
        
        public int getBlanket() {
            return this.general / 2 + this.blanket;
        }
        
        public int getForehead() {
            return (this.general + this.face) / 5 + this.forehead;
        }
        
        public int getNoseBridge() {
            return (this.general + this.face) / 5 + this.noseBridge;
        }
        
        public int getMuzzle() {
            return (this.general + this.face) / 5 + this.muzzle;
        }
    }
}
