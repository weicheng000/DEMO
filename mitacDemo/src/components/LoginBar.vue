<template>
  <v-card
    class="mx-auto pa-12 pb-8 mt-4"
    elevation="8"
    max-width="600"
    rounded="lg"
  >
    <v-card-title
      class="mb-4"
    >
      <h2>Welcome Login My Demo!</h2>
    </v-card-title>

    <v-text-field
      v-model="email"
      :readonly="loading"
      :rules="[required]"
      class="mb-4"
      label="Account"
    ></v-text-field>

    <v-text-field
      v-model="password"
      class="mb-4"
      :type="show ? 'text' : 'password'"
      :append-inner-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
      :readonly="loading"
      :rules="[required]"
      label="Password"
      placeholder="Enter your password"
      @click:append-inner="show = !show"
    ></v-text-field>

    <v-card class="mb-4" color="surface-variant" variant="tonal">
      <v-card-text class="text-medium-emphasis text-caption">
        Warning: After 3 consecutive failed login attempts, you account will
        be temporarily locked for three hours. If you must login now, you can
        also click "Forgot login password?" below to reset the login password.
      </v-card-text>
    </v-card>

    <v-btn
      :loading="loading"
      block
      color="info"
      size="large"
      type="button"
      variant="elevated"
      @click="submitLogin"
    >
      Sign In
    </v-btn>

  </v-card>
</template>

<script setup>
import {ref} from "vue";
import router from "@/router";
import store from "@/store"

const email = ref(null);
const password = ref(null);
const loading = ref(false);
const show = ref(false);
const url = "/api/login"

const required = (v) => {
  return !!v || 'Field is required'
};

const submitLogin = async () => {
  loading.value = true;

  let loginData;

  if (email.value && password.value){
    loginData = {
      username: email.value,
      password: password.value
    }
  }else {
    console.log("bad");
    loading.value = false;
    return;
  }

  const response = await fetch(url,{
    method: "POST",
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(loginData)
  })

  const result = await response.json();

  if (result.code === 1){
    const token = result.data;
    store.commit('setToken',token)

    await router.push("/table");
  }else {
    console.log("false");
  }

  loading.value = false;
}

</script>

<style scoped>

</style>
