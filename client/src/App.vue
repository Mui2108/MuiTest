<template>
  <v-app>
    <v-app-bar app>
      <v-toolbar-title class="headline text-uppercase">
        <span></span>
        <span class="font-weight-light"> ระบบโรงภาพยนตร์</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn
        text
        href="https://github.com/vuetifyjs/vuetify/releases/latest"
        target="_blank"
      >
        <span class="mr-2">Username : {{username}}</span>
      </v-btn>
    </v-app-bar>
    <v-content>
      <router-view />
    </v-content>
  </v-app>
</template>
<script>

export default {
  name: 'App',
  data: () => ({
    username: 'Wasin Mainchaiyaphum',
  }),
};
</script>
