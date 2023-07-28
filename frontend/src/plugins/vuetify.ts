import { createVuetify, ThemeDefinition } from "vuetify";
import "vuetify/styles";
import { mdi, aliases } from "vuetify/iconsets/mdi-svg";

export enum AppTheme {
  LIGHT = "light",
  DARK = "dark",
  CUSTOM = "custom",
}

const light: ThemeDefinition = {
  dark: false,
  colors: {
    primary: "#FF7200",
    secondary: "#5CBBF6",
  },
};

const dark: ThemeDefinition = {
  dark: true,
  colors: {
    primary: "#1867C0",
    secondary: "#5CBBF6",
  },
};

export default createVuetify({
  theme: {
    defaultTheme: "dark",
    themes: {
      light,
      dark,
    },
  },
  icons: {
    defaultSet: "mdi",
    aliases: {
      ...aliases,
    },
    sets: {
      mdi,
    },
  },
  defaults: {
    VTextField: { variant: "outlined", density: "comfortable" },
    VSelect: { variant: "outlined", density: "comfortable" },
    VTextarea: { variant: "outlined", density: "comfortable" },
  },
});
