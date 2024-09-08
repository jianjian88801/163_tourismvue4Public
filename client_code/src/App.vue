<template>
  <router-view />
</template>
<script>
const debounce = (fn, delay) => {
  let timer = null
  return function () {
    let context = this
    let args = arguments
    clearTimeout(timer)
    timer = setTimeout(function () {
      fn.apply(context, args)
    }, delay)
  }
}

const _ResizeObserver = window.ResizeObserver
window.ResizeObserver = class ResizeObserver extends _ResizeObserver {
  constructor(callback) {
    callback = debounce(callback, 16)
    super(callback)
  }
}
</script>
<style lang="scss">
body {
  margin: 0;
}
* {
  box-sizing: border-box;
}
.app-contain {
  width: 100%;
  // padding: 0 8%;
  // box-sizing: border-box;
}
.section_title {
  padding: 0 0 0 16px;
  margin: 0 auto;
  color: #fff;
  font-weight: 500;
  letter-spacing: 0px;
  font-size: 16px;
  border-color: #eee;
  line-height: 38px;
  border-radius: 0;
  background: url(http://localhost:8080/tourism/file/cf02fa1d4a2442b581a3d62afcc09015.png)
      no-repeat left center,
    #fbfbfb;
  border-width: 1px;
  border-style: solid;
  text-align: left;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  // text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
