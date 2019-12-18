<template>
  <v-app>
  

   
    <v-content> 
      <center>
      <HelloWorld/>
      </center>
    </v-content>
  </v-app>
</template>

<script>
import HelloWorld from './components/HelloWorld';

export default {
  name: 'App',

  components: {
    HelloWorld,
  },

  data: () => ({
    //
  }),
};
</script>
