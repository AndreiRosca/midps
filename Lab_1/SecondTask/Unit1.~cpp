//---------------------------------------------------------------------------

#include <vcl.h>
#pragma hdrstop

#include "Unit1.h"
#include "dos.h"
#include <stdio.h>
//---------------------------------------------------------------------------
#pragma package(smart_init)
#pragma link "sButton"
#pragma link "sEdit"
#pragma link "sLabel"
#pragma resource "*.dfm"

TTimerForm *TimerForm;

static struct time &operator +=( struct time &lhs, int hundredsOfASecond) {
        lhs.ti_hund += hundredsOfASecond;
        if (lhs.ti_hund >= 100) {
                ++lhs.ti_sec;
                lhs.ti_hund = 0;
        }
        if (lhs.ti_sec >= 60) {
                ++lhs.ti_min;
                lhs.ti_sec = 0;
                if (lhs.ti_min >= 60) {
                        ++lhs.ti_hour;
                        lhs.ti_min = 0;
                        if (lhs.ti_hour >= 24)
                                lhs.ti_hour = 0;
                }
        }
        return lhs;
}

//---------------------------------------------------------------------------
__fastcall TTimerForm::TTimerForm(TComponent* Owner)
        : TForm(Owner)
{
        Timer1->Enabled = false;
        Timer2->Enabled = false;
        stopButton->Enabled = false;
}
//---------------------------------------------------------------------------
void __fastcall TTimerForm::onExitButtonClick(TObject *Sender)
{
        Close();
}
//---------------------------------------------------------------------------

void __fastcall TTimerForm::onStartButtonClick(TObject *Sender)
{
        Timer1->Enabled = true;
        Timer2->Enabled = true;
        stopButton->Enabled = true;
        startButton->Enabled = !stopButton->Enabled;
        zeroButton->Enabled = startButton->Enabled;
}
//---------------------------------------------------------------------------

void __fastcall TTimerForm::onStopButtonClick(TObject *Sender)
{
        Timer1->Enabled = false;
        Timer2->Enabled = false;
        stopButton->Enabled = false;
        startButton->Enabled = !stopButton->Enabled;
        zeroButton->Enabled = startButton->Enabled;  
}
//---------------------------------------------------------------------------

void __fastcall TTimerForm::onFirstTimerCall(TObject *Sender)
{
        static struct time stopwatchTime = {0};
        stopwatchTime += 10;

        const int MAX_BUFFER_SIZE = 1024;
        char buffer[MAX_BUFFER_SIZE] = {0};
        sprintf(buffer, "%02d:%02d:%02d:%02d", stopwatchTime.ti_hour,
                stopwatchTime.ti_min,
                stopwatchTime.ti_sec, stopwatchTime.ti_hund);
                
        stopwatchEditor->Text = buffer;
}

//---------------------------------------------------------------------------

void __fastcall TTimerForm::onTimerTimerCall(TObject *Sender)
{
        const int MAX_BUFFER_SIZE = 1024;
        struct date currentDate;
        struct time currentTime;
        getdate(&currentDate);
        gettime(&currentTime);

        char buffer[MAX_BUFFER_SIZE] = {0};
        sprintf(buffer, "%02d-%02d-%4d %02d:%02d:%02d", currentDate.da_mon,
                currentDate.da_day, currentDate.da_year, currentTime.ti_hour,
                currentTime.ti_min, currentTime.ti_sec);
                
    timeEditor->Text = buffer;
}
//---------------------------------------------------------------------------

void __fastcall TTimerForm::onZeroButtonClick(TObject *Sender)
{
        stopwatchEditor->Text = "00:00:00:00";
        //timeEditor->Text = "";
}
//---------------------------------------------------------------------------



