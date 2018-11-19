package com.jiayang.mvp.mvpframework.widget.pickerview.lib;

final class OnItemSelectedRunnable implements Runnable {
    final WheelView wheelView;

    OnItemSelectedRunnable(WheelView wheelView) {
        this.wheelView = wheelView;
    }

    @Override
    public final void run() {
        wheelView.onItemSelectedListener.onItemSelected(wheelView.getCurrentItem());
    }
}
