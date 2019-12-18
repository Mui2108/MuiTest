<template>
  <v-content>
    
    <br>
    <br>
  <v-card
    class="mx"
    style="max-width: 500px;"
  >
    <v-toolbar
      color="#009688"
      cards
      dark
      flat
    > <v-icon>mdi-account-plus</v-icon>&nbsp;&nbsp;
      <v-card-title class="title font-weight-regular">สมัคสมาชิก</v-card-title>
     
      <v-spacer></v-spacer>

    </v-toolbar>
    <v-form
      ref="form"
      v-model="form"
      class="pa-4 pt-6"
    >
    <table style="width:100%">
<tr>
    <th>
    <v-text-field 
        v-model="username"
        filled
        color="deep-purple"
        label="Username"
      ></v-text-field>
    </th>
    
    <th>
      <v-text-field
        v-model="password"
        :rules="[rules.password, rules.length(8)]"
        filled
        color="deep-purple"
        counter="8"
        label="Password"
        type="password"
      ></v-text-field>
    </th>
    </tr>
    </table>
      <v-text-field
        v-model="name"
        filled
        color="deep-purple"
        label="ชื่อ"
      ></v-text-field>


        <v-slider
              v-model="form.age"
              :rules="rules.age"
              color="#00BCD4"
              label="อายุ"
              hint="Be honest"
              min="5"
              max="100"
              thumb-label
            ></v-slider>
    
<table style="width:100%">
  <tr>
  <th>
        <v-select
        v-model="genderSelect"
          :items="genders"
          item-text="name"
          item-value="id"
          filled
          label="เพศ"
        ></v-select>
  </th>
 <th>
        <v-select
        v-model="jobsSelect"
          :items="jobs"
          filled
          label="อาชีพ"
        ></v-select>
  </th>
  </tr>
</table>

 <v-text-field
        v-model="email"
        :rules="[rules.email]"
        filled
        color="deep-purple"
        label="Email address"
        type="email"
      ></v-text-field>

<table style="width:100%">
  <th>
<v-select
                  v-model="countrySelected"
                  :items="countrys"
                  label="ประเทศ"
                  
                ></v-select>
  </th>
<th>
  
        <v-text-field
           v-model="tel"
            label="เบอร์โทร" 
            
          ></v-text-field>

  </th>
</table>
    </v-form>
    <v-divider></v-divider>
    <v-card-actions>
      <v-btn
        text
        @click="$refs.form.reset()"
      >
        Clear
      </v-btn>
      <v-spacer></v-spacer>

     <v-btn color="#009688" @click="save" dark>สมัครสมาชิก
       <v-icon dark right>mdi-checkbox-marked-circle</v-icon>
     </v-btn>
      
    </v-card-actions>
    <v-dialog
      v-model="dialog"
      absolute
      max-width="200"
      persistent
    >
      
    </v-dialog>
  </v-card>
   </v-content>
</template>

<script>
  export default {



    data: () => ({
      agreement: false,
      dialog: false,
      email: undefined,
      form: false,
      isLoading: false,
      password: undefined,
      phone: undefined,
      age : undefined,
      rules: {
         age: [
            val => val < 70 && val > 5 || `I don't believe you!`,
          ],
        email: v => (v || '').match(/@/) || 'Please enter a valid email',
        length: len => v => (v || '').length >= len || `โปรดใส่รหัสผ่านให้ครับ  ${len} ตัว`,
        password: v => (v || '').match() ||
          'โปรดใส่รหัสผ่านให้ครับ 8 ตัว',
        required: value => !!value || 'Required.',
          min: v => v.length >= 8 || 'Min 8 characters',
      },
    }),
  }
</script>