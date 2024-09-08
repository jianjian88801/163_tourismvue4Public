<template>
	<div>
		<p v-if="msTime.show" style="margin: 0;">
			<span v-if="tipShow">{{tipText}}:</span>
			<span v-if="!tipShow">{{tipTextEnd}}:</span>
			<span v-if="msTime.day>0"><span>{{msTime.day}}</span><i>{{dayTxt}}</i></span>
			<span>{{msTime.hour}}</span><i>{{hourTxt}}</i>
			<span>{{msTime.minutes}}</span><i>{{minutesTxt}}</i>
			<span>{{msTime.seconds}}</span><i>{{secondsTxt}}</i>
		</p>
		<p v-if="!msTime.show">{{endText}}</p>
	</div>
</template>

<script setup>
	import {
		ref,
		toRefs,
		watch,
	} from 'vue';
	const emit = defineEmits(['end_callback','start_callback'])
	const tipShow = ref(true)
	const msTime = ref({
		show: false, //倒计时状态
		day: '', //天
		hour: '', //小时
		minutes: '', //分钟
		seconds: '' //秒
	})
	const star = ref('')
	const end = ref('')
	const current = ref('')

	const props = defineProps({
		//距离开始提示文字
		tipText: {
			type: String,
			default: '距离开始'
		},
		//距离结束提示文字
		tipTextEnd: {
			type: String,
			default: '距离结束'
		},
		//时间控件ID
		id: {
			type: String,
			default: '1'
		},
		//当前时间
		currentTime: {
			type: Number
		},
		// 活动开始时间
		startTime: {
			type: Number
		},
		// 活动结束时间
		endTime: {
			type: Number
		},
		// 倒计时结束显示文本
		endText: {
			type: String,
			default: '已结束'
		},
		//自定义显示文字:天
		dayTxt: {
			type: String,
			default: ':'
		},
		//自定义显示文字:时
		hourTxt: {
			type: String,
			default: ':'
		},
		//自定义显示文字:分
		minutesTxt: {
			type: String,
			default: ':'
		},
		secondsTxt: {
			type: String,
			default: ':'
		},
		//是否开启秒表倒计，未完成
		secondsFixed: {
			type: Boolean,
			default: false
		},
	})
	const {
		tipText,
		tipTextEnd,
		id,
		currentTime,
		startTime,
		endTime,
		endText,
		dayTxt,
		hourTxt,
		minutesTxt,
		secondsTxt,
		secondsFixed,
	} = toRefs(props)
	watch(currentTime, () => {
		gogogo()
	})
	const gogogo = () => {
		//判断是秒还是毫秒
		startTime.value.toString().length == 10 ? star.value = startTime.value * 1000 : star.value = startTime.value
		endTime.value.toString().length == 10 ? end.value = endTime.value * 1000 : end.value = endTime.value
		if (currentTime.value) {
			currentTime.value.toString().length == 10 ? current.value = currentTime.value * 1000 : current.value =
				currentTime.value
		} else {
			current.value = (new Date()).getTime();
		}

		if (end.value < current.value) {
			/**
			 * 结束时间小于当前时间 活动已结束
			 */
			msTime.value.show = false;
			end_message();
		} else if (current.value < star.value) {
			/**
			 * 当前时间小于开始时间 活动尚未开始
			 */
			tipShow.value = true
			setTimeout(() => {
				runTime(star.value, current.value, start_message.value);
			}, 1);
		} else if (end.value > current.value && star.value < current.value || star.value == current.value) {
			/**
			 * 结束时间大于当前并且开始时间小于当前时间，执行活动开始倒计时
			 */
			tipShow.value = false
			msTime.value.show = true;
			emit('start_callback', msTime.value.show)
			setTimeout(() => {
				runTime(end.value, current.value, end_message.value, true)
			}, 1);
		}
	}
	const runTime = (startTime, endTime, callFun, type) => {
		let msTimes = msTime.value
		let timeDistance = startTime - endTime;
		if (timeDistance > 0) {
			msTime.value.show = true;
			msTimes.day = Math.floor(timeDistance / 86400000);
			timeDistance -= msTimes.day * 86400000;
			msTimes.hour = Math.floor(timeDistance / 3600000);
			timeDistance -= msTimes.hour * 3600000;
			msTimes.minutes = Math.floor(timeDistance / 60000);
			timeDistance -= msTimes.minutes * 60000;
			//是否开启秒表倒计,未完成
			msTimes.seconds = Math.floor(timeDistance / 1000).toFixed(0);
			timeDistance -= msTimes.seconds * 1000;

			if (msTimes.hour < 10) {
				msTimes.hour = "0" + msTimes.hour;
			}
			if (msTimes.minutes < 10) {
				msTimes.minutes = "0" + msTimes.minutes;
			}
			if (msTimes.seconds < 10) {
				msTimes.seconds = "0" + msTimes.seconds;
			}
			let _s = Date.now();
			let _e = Date.now();
			let diffPerFunc = _e - _s;
			setTimeout(() => {
				if (type) {
					runTime(end.value, endTime += 1000, callFun, true);
				} else {
					runTime(star.value, endTime += 1000, callFun);
				}
			}, 1000 - diffPerFunc)
		} else {
			callFun();
		}
	}
	const start_message = () => {
        tipShow.value = false
        emit('start_callback', msTime.value.show);
        setTimeout(() => {
          runTime(end.value, star.value, end_message.value, true)
        }, 1);
      }
    const  end_message = () => {
        msTime.value.show = false;
        if (currentTime.value <= 0) {
          return;
        }
        emit('end_callback', msTime.value.show);
      }
	gogogo()
</script>

<style>
</style>