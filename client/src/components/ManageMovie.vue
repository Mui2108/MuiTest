<template>
  <div class="main">
    <v-container fluid>

      <center>
        <h1>จัดการรอบฉายภาพยนตร์</h1>
      </center>

      <v-row align="center">
        <label>ภาพยนตร์</label>
        <v-col class="d-flex" cols="12">
          <v-select
            label="เลือกภาพยนตร์"
            outlined
            v-model="managemovie.movie_id"
            :items="movieAdd"
            item-text="name"
            item-value="id"
            :rules="[(v) => !!v || 'Item is required']"
            required
            solo
          ></v-select>
        </v-col>

        <label>ภาษา</label>
        <v-col class="d-flex" cols="12">
          <v-select
            label="เลือกภาษา"
            outlined
            v-model="managemovie.dub_id"
            :items="dub"
            item-text="langauge"
            item-value="idL"
            :rules="[(v) => !!v || 'Item is required']"
            required
            solo
          ></v-select>
        </v-col>

        <label>ห้องฉาย</label>
        <v-col class="d-flex" cols="12">
          <v-select
            label="เลือกห้องฉายภาพยนตร์"
            outlined
            v-model="managemovie.room_id"
            :items="room"
            item-text="room"
            item-value="idR"
            :rules="[(v) => !!v || 'Item is required']"
            required
            solo
          ></v-select>
        </v-col>

         <v-row justify="center">
          <v-col cols="12" lg="6">
            <label>วันที่ฉาย:</label>
            <v-menu
              ref="menu1"
              v-model="menu1"
              :close-on-content-click="false"
              transition="scale-transition"
              offset-y
              full-width
              max-width="290px"
              min-width="290px"
            >
            <template v-slot:activator="{ on }">
              <v-text-field
                v-model="dateFormatted"
                label="กรุณากรอกวันที่"
                @blur="date = parseDate(dateFormatted)"
                v-on="on"
              ></v-text-field>
            </template>
                <v-date-picker v-model="date" no-title @input="menu1 = false"></v-date-picker>
            </v-menu>
          </v-col>
        </v-row>

        <label>เวลาฉาย</label>
        <v-col class="d-flex" cols="5">
          <v-select
            label="--:--"
            outlined
            v-model="managemovie.movie_time"
            :items="movieTime"
            item-text="time"
            item-value="idT"
            :rules="[(v) => !!v || 'Item is required']"
            required
            solo
          ></v-select>
          </v-col>


        <v-col class="center" cols="12" sm="12">
          <v-btn color="success" width="150" @click="saveManageMovie" >บันทึก</v-btn>
        </v-col>

      </v-row>
    </v-container>
  </div>
</template>

<script>
import http from "../http-common";
export default {
  data () {
    return {
    movieAdd: [],
    dub: [],
    room: [],
    movieTime: [],
    date: new Date().toISOString().substr(0, 10),
      dateFormatted: this.formatDate(new Date().toISOString().substr(0, 10)),
      menu1: false,
    managemovie: [
      {
      movie_id: "",
      dub_id: "",
      room_id: "",
      movie_time: "" 
    }
    ]
    };
  },
  computed: {
    computedDateFormatted() {
      return this.formatDate(this.date);
    }
  },
  watch: {
    date() {
      this.dateFormatted = this.formatDate(this.date);
    }
  },
  methods: {
    /* eslint-disable no-console */
    getMovieAdd() {
      http
        .get("/movie")
        .then(response => {
          this.movieAdd = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getDub() {
      http
        .get("/language")
        .then(response => {
          this.dub = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getRoom() {
      http
        .get("/theater")
        .then(response => {
          this.room = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getTime() {
      http
        .get("/time")
        .then(response => {
          this.movieTime = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    saveManageMovie() {
      http
        .post(
          "/managemovie/" +
            this.managemovie.movie_id +
            "/" +
            this.managemovie.dub_id +
            "/" +
            this.managemovie.room_id +
            "/" + 
            this.managemovie.movie_time +
            "/" + 
            this.date,this.managemovie
        )
        .then(response => {
          console.log(response);
          alert("บันทึกข้อมูลสำเร็จ");
        })
        .catch(e => {
          console.log(e);
        });
    
  },
  
  formatDate(date) {
      if (!date) return null;
      const [year, month, day] = date.split("-");
      return `${month}/${day}/${year}`;
    },
    parseDate(date) {
      if (!date) return null;
      const [month, day, year] = date.split("/");
      return `${year}-${month.padStart(2, "0")}-${day.padStart(2, "0")}`;
    }
},
  mounted() {
    this.getMovieAdd();
    this.getDub();
    this.getRoom();
    this.getTime();
  }
};
</script>
<style scoped>
.main {
  margin: 100px 300px;
}
.center {
  margin: 0px 160px;
}
</style>